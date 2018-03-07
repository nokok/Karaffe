package org.karaffe.compiler.tree.transform.namer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.transform.AbstractTransformer;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.names.SimpleName;

import karaffe.core.Console;

public class BuiltinFunctionTransformer extends AbstractTransformer {

    private final List<String> builtInFunctionNames;

    public BuiltinFunctionTransformer() {
        super("built-in-function-transformer");
        this.builtInFunctionNames = Stream.of(Console.class.getMethods()).map(Method::getName).collect(Collectors.toList());
    }

    @Override
    public Apply transformBody(Apply oldApply) {
        SimpleName methodName = oldApply.getMethodName();

        if (isBuiltInFunctionName(methodName)) {
            return new Apply(
                    oldApply.getPosition(),
                    transform(oldApply.getExpression()),
                    transform(oldApply.getMethodName()),
                    oldApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
        }
        return new Apply(
                oldApply.getPosition(),
                transform(oldApply.getExpression()),
                transform(oldApply.getMethodName()),
                oldApply.getArgs().stream().map(this::transform).collect(Collectors.toList()));
    }

    private boolean isBuiltInFunctionName(SimpleName name) {
        return this.builtInFunctionNames.contains(name.toString());
    }

}
