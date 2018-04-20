package org.karaffe.compiler.frontend.karaffe.transformer;

import org.karaffe.compiler.base.generator.ConsecutiveNumberGenerator;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.NameRef;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Block;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionType;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.NewInstance;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Return;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.StaticApply;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetFieldDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetLocalDef;

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
            case APPLY:
                return transform((Apply) expression);
            case BLOCK:
                return transform((Block) expression);
            case INT_LITERAL:
                scope.add(new LetLocalDef(new SimpleName(this.nameGen.generate()), expression));
                return scope.flatten();
            case STATIC_APPLY:
                return transform((StaticApply) expression);
            case NEW_INSTANCE:
                return transform((NewInstance) expression);
            default:
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
    public Expression transform(StaticApply staticApply) {
        Block scope = new Block();
        List<? extends Expression> args = staticApply.getArgs().stream().map(this::transform).collect(Collectors.toList());

        if (staticApply.getArgs().size() != args.size()) {
            throw new IllegalStateException();
        }
        args.forEach(scope::add);
        List<ExpressionName> argNames = args.stream().map(NameRef::asExprName).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        StaticApply newStaticApply = new StaticApply(staticApply.getPosition(), staticApply.getTypeName(), staticApply.getMethodName(), argNames);
        scope.add(newStaticApply);
        return scope.flatten();
    }

    @Override
    public Expression transform(NewInstance oldNewInstance) {
        Block scope = new Block();
        List<Expression> args = oldNewInstance.getArgs().stream().map(this::transform).collect(Collectors.toList());
        if (oldNewInstance.getArgs().size() != args.size()) {
            throw new IllegalStateException();
        }
        args.forEach(scope::add);
        List<ExpressionName> argNames = args.stream().map(NameRef::asExprName).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        NewInstance newInstance = new NewInstance(oldNewInstance.getPosition(), oldNewInstance.getTypeName(), argNames);
        scope.add(new LetLocalDef(new SimpleName(this.nameGen.generate()), newInstance));
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
