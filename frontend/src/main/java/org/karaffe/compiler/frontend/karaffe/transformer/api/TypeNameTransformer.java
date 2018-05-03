package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.stream.Collectors;

public interface TypeNameTransformer extends SimpleNameTransformer, FullyQualifiedNameTransformer {

    public default void onTypeNameBefore(TypeName typeName) {

    }

    public default void onTypeNameAfter(TypeName typeName) {

    }

    public default TypeName transform(TypeName oldTypeName) {
        if (oldTypeName.isFullyQualified()) {
            return transform((FullyQualifiedTypeName) oldTypeName);
        }
        onTypeNameBefore(oldTypeName);
        TypeName after = transformBody(oldTypeName);
        onTypeNameAfter(after);
        return after;
    }

    public default TypeName transformBody(TypeName typeName) {
        return new TypeName(
                typeName.getPosition(),
                transform(typeName.getName()),
                typeName.getParameterizedTypes().stream().map(this::transform).collect(Collectors.toList()),
                typeName.isArrayType());
    }
}
