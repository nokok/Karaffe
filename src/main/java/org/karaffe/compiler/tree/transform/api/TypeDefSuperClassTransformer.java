package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface TypeDefSuperClassTransformer extends SimpleNameTransformer {

    public default void onSuperClassBefore(TypeDefStatement parent, SimpleName superClass) {

    }

    public default void onSuperClassAfter(TypeDefStatement parent, SimpleName superClass) {

    }

    public default SimpleName transformOnTypeDefSuperClass(TypeDefStatement parent, SimpleName superClass) {
        onSuperClassBefore(parent, superClass);
        SimpleName after = transform(superClass);
        onSuperClassAfter(parent, after);
        return after;
    }
}
