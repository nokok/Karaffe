package org.karaffe.compiler.transformer;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.NameRef;
import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.expressions.Apply;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.expressions.ExpressionType;
import org.karaffe.compiler.ast.expressions.Return;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;
import org.karaffe.compiler.ast.statements.LetDef;
import org.karaffe.compiler.ast.statements.LetFieldDef;
import org.karaffe.compiler.ast.statements.LetLocalDef;
import org.karaffe.compiler.base.generator.ConsecutiveNumberGenerator;
import org.karaffe.compiler.transformer.AbstractTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class KNormalizer extends AbstractTransformer {

    private final ConsecutiveNumberGenerator nameGen = new ConsecutiveNumberGenerator("k_");

    public KNormalizer() {
        super("k-normalize");
    }

    @Override
    public LetFieldDef transform(LetFieldDef otherMember) {
        super.onLetFieldDefBefore(otherMember);
        LetDef letDef = otherMember;
        Expression initializer = letDef.getInitializer().map(this::transform).orElse(null);
        TypeName typeName = letDef.getTypeName().orElse(null);
        LetFieldDef letFieldDef = new LetFieldDef(letDef.getPosition(), letDef.getName(), typeName, initializer);
        super.onLetFieldDefAfter(letFieldDef);
        return letFieldDef;
    }

    @Override
    public LetLocalDef transform(LetLocalDef otherLocalLetDef) {
        Block scope = new Block();
        Optional<Expression> transformedInitializer = otherLocalLetDef.getInitializer().map(this::transform);
        transformedInitializer.ifPresent(scope::add);
        transformedInitializer.flatMap(NameRef::asExprName).map(ExpressionName::new).map(Return::new).ifPresent(scope::add);
        return new LetLocalDef(otherLocalLetDef.getPosition(), otherLocalLetDef.getName(), otherLocalLetDef.getTypeName().orElse(null), scope.flatten());
    }

    @Override
    public Expression transform(Expression expression) {
        Block scope = new Block();
        if (!expression.isNormalizable()) {
            scope.add(new LetLocalDef(new SimpleName(this.nameGen.generate()), expression));
            return scope.flatten();
        }
        switch (expression.getExpressionType()) {
        case APPLY: {
            Expression transformed = transform((Apply) expression);
            return transformed;
        }
        case BLOCK: {
            Expression transformed = transform((Block) expression);
            return transformed;
        }
        case INT_LITERAL:
            scope.add(new LetLocalDef(new SimpleName(this.nameGen.generate()), expression));
            return scope.flatten();
        default:
            System.out.println(expression.getExpressionType());
            return expression;
        }
    }

    @Override
    public Expression transform(Apply apply) {
        Block scope = new Block();
        Expression expr;
        Expression originalExpr = apply.getExpression();
        if (originalExpr.getExpressionType().equals(ExpressionType.NAME)) {
            expr = originalExpr;
        } else {
            Expression transformed = transform(originalExpr);
            scope.add(transformed);
            expr = transformed.asExprName().orElseThrow(IllegalStateException::new);
        }
        SimpleName methodName = apply.getMethodName();
        List<? extends Expression> args = apply
                .getArgs()
                .stream()
                .map(this::transform)
                .collect(Collectors.toList());
        if (args.size() != args.stream().map(NameRef::asExprName).count()) {
            throw new IllegalStateException();
        }
        args.forEach(scope::add);
        List<ExpressionName> argsNames = args
                .stream()
                .map(NameRef::asExprName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Apply ret = new Apply(apply.getPosition(), expr, methodName, argsNames);
        scope.add(new LetLocalDef(new SimpleName(this.nameGen.generate()), ret));
        return scope.flatten();
    }

    @Override
    public Block transform(Block expression) {
        Block scope = new Block();
        Block block = expression.flatten();
        List<Statement> statements = new ArrayList<>();
        for (Statement t : block.getBody()) {
            statements.add(transform(t));
        }
        statements.forEach(scope::add);
        return scope.flatten();
    }

}
