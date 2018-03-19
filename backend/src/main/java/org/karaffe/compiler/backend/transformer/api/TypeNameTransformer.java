package org.karaffe.compiler.backend.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

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
                typeName.getParameterizedTypes().stream().map(this::transform).collect(Collectors.toList()));
    }
}
