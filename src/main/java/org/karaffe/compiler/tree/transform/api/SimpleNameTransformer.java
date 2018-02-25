package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface SimpleNameTransformer {

    public default void onSimpleName(SimpleName simpleName) {

    }

    public default SimpleName transform(SimpleName oldName) {
        onSimpleName(oldName);
        return new SimpleName(oldName);
    }
}
