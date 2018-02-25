package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.FullyQualifiedName;

public interface FullyQualifiedNameTransformer {

    public default void onFullyQualifiedName(FullyQualifiedName name) {

    }

    public default FullyQualifiedName transform(FullyQualifiedName oldName) {
        onFullyQualifiedName(oldName);
        return new FullyQualifiedName(oldName);
    }
}
