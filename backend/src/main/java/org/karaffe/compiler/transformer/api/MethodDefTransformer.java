package org.karaffe.compiler.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.statements.MethodDef;

public interface MethodDefTransformer extends BaseTransformer, ParametersTransformer {

    public default void onMethodBefore(MethodDef methodDef) {

    }

    public default void onMethodAfter(MethodDef methodDef) {

    }

    public default MethodDef transform(MethodDef methodDef) {
        onMethodBefore(methodDef);
        MethodDef after = transformBody(methodDef);
        onMethodAfter(after);
        return after;
    }

    public default MethodDef transformBody(MethodDef methodDef) {
        return new MethodDef(
                methodDef.getPosition(),
                methodDef.getModifiers(),
                transform(methodDef.getReturnTypeName()),
                transform(methodDef.getName()),
                methodDef.getParameters().stream().map(this::transform).collect(Collectors.toList()),
                methodDef.getBody().stream().map(this::transform).collect(Collectors.toList()));
    }

}
