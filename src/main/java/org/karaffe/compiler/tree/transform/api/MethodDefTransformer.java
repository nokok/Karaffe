package org.karaffe.compiler.tree.transform.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.api.Modifier;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

public interface MethodDefTransformer extends BaseTransformer, ParametersTransformer {

    public default void onMethodBefore(MethodDef methodDef) {

    }

    public default void onMethodAfter(MethodDef methodDef) {

    }

    public default MethodDef transform(MethodDef methodDef) {
        onMethodBefore(methodDef);
        MethodDef after = new MethodDef(
                methodDef.getPosition(),
                transformMethodModifier(methodDef, methodDef.getModifiers()),
                transform(methodDef.getReturnTypeName()),
                transform(methodDef.getName()),
                transform(methodDef.getParameters()),
                methodDef.getBody().stream().map(this::transform).collect(Collectors.toList()));
        onMethodAfter(after);
        return after;
    }

    public default List<? extends Modifier> transformMethodModifier(MethodDef parent, List<? extends Modifier> modifiers) {
        return new ArrayList<>(modifiers);
    }

}
