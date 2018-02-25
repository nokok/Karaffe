package org.karaffe.compiler.tree.v2.api;

import java.util.Optional;

import org.karaffe.compiler.tree.v2.expressions.ExpressionName;

public interface NameRef {

    public default Optional<ExpressionName> asExprName() {
        return Optional.empty();
    }
}
