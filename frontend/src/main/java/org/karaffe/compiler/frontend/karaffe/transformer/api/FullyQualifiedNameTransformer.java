package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;

public interface FullyQualifiedNameTransformer {

    public default void onFullyQualifiedNameBefore(FullyQualifiedTypeName name) {

    }

    public default void onFullyQualifiedNameAfter(FullyQualifiedTypeName name) {

    }

    public default FullyQualifiedTypeName transform(FullyQualifiedTypeName oldName) {
        onFullyQualifiedNameBefore(oldName);
        FullyQualifiedTypeName after = transformBody(oldName);
        onFullyQualifiedNameAfter(after);
        return after;
    }

    public default FullyQualifiedTypeName transformBody(FullyQualifiedTypeName name) {
        return new FullyQualifiedTypeName(name);
    }
}
