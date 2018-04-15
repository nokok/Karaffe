package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

public interface TypeDefSuperClassTransformer extends SimpleNameTransformer {

    public default void onSuperClassBefore(TypeDefStatement parent, SimpleName superClass) {

    }

    public default void onSuperClassAfter(TypeDefStatement parent, SimpleName superClass) {

    }

    public default SimpleName transformBodyOnTypeDefSuperClass(TypeDefStatement parent, SimpleName superClass) {
        return new SimpleName(transform(superClass));
    }

    public default SimpleName transformOnTypeDefSuperClass(TypeDefStatement parent, SimpleName superClass) {
        onSuperClassBefore(parent, superClass);
        SimpleName after = transformBodyOnTypeDefSuperClass(parent, superClass);
        onSuperClassAfter(parent, after);
        return after;
    }
}
