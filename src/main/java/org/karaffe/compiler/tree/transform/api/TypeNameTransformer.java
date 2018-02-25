package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.TypeName;

public interface TypeNameTransformer {

    public default void onTypeName(TypeName typeName) {

    }

    public default TypeName transform(TypeName oldTypeName) {
        onTypeName(oldTypeName);
        return new TypeName(oldTypeName);
    }
}
