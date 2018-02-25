package org.karaffe.compiler.tree.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.NameRef;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetDef;
import org.karaffe.compiler.tree.v2.statements.LetFieldDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.util.NameGen;

public class KNormalizer implements BaseDefaultTransformer {

    public static final String name = "k-normalize";
    private final NameGen nameGen = new NameGen("k_");

    @Override
    public String getTransformerName() {
        return KNormalizer.name;
    }

    @Override
    public LetFieldDef transform(LetFieldDef otherMember) {
        LetDef letDef = otherMember;
        Expression initializer = letDef.getInitializer().map(this::transform).orElse(null);
        SimpleName typeName = letDef.getTypeName().orElse(null);
        return new LetFieldDef(letDef.getPosition(), letDef.getName(), typeName, initializer);
    }

    @Override
    public LetLocalDef transform(LetLocalDef otherLocalLetDef) {
        Block scope = new Block();
        Optional<Expression> transformedInitializer = otherLocalLetDef.getInitializer().map(this::transform);
        transformedInitializer.ifPresent(scope::add);
        Optional<SimpleName> typeNameOpt = otherLocalLetDef.getTypeName();
        Optional<ExpressionName> exprNameOpt = transformedInitializer.flatMap(NameRef::asExprName);
        exprNameOpt.map(ExpressionName::new).ifPresent(scope::add);
        return new LetLocalDef(otherLocalLetDef.getPosition(), otherLocalLetDef.getName(), typeNameOpt.orElse(null), scope.flatten());
    }

    @Override
    public Expression transform(Expression expression) {
        Block scope = new Block();
        if (!expression.isNormalizable()) {
            scope.add(new LetLocalDef(this.nameGen.genSimpleName(), expression));
            return scope.flatten();
        }
        switch (expression.getExpressionType()) {
        case APPLY: {
            return transform((Apply) expression);
        }
        case BLOCK: {
            return transform((Block) expression);
        }
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
        if (originalExpr.isTermNode()) {
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
        scope.add(new LetLocalDef(this.nameGen.genSimpleName(), ret));
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
