package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.TypeName;

public interface TypeNameTransformer {

    public default void onTypeNameBefore(TypeName typeName) {

    }

    public default void onTypeNameAfter(TypeName typeName) {

    }

    public default TypeName transform(TypeName oldTypeName) {
        onTypeNameBefore(oldTypeName);
        TypeName after = transformBody(oldTypeName);
        onTypeNameAfter(after);
        return after;
    }

    public default TypeName transformBody(TypeName typeName) {
        return new TypeName(typeName);
    }
}
