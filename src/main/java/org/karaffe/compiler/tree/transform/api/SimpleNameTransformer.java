package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface SimpleNameTransformer {

    public default void onSimpleNameBefore(SimpleName simpleName) {

    }

    public default void onSimpleNameAfter(SimpleName simpleName) {

    }

    public default SimpleName transform(SimpleName oldName) {
        onSimpleNameBefore(oldName);
        SimpleName after = transformBody(oldName);
        onSimpleNameAfter(after);
        return after;
    }

    public default SimpleName transformBody(SimpleName simpleName) {
        return new SimpleName(simpleName);
    }
}
