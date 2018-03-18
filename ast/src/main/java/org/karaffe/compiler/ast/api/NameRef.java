package org.karaffe.compiler.ast.api;

import java.util.Optional;

import org.karaffe.compiler.ast.expressions.ExpressionName;

public interface NameRef {

    public default Optional<ExpressionName> asExprName() {
        return Optional.empty();
    }
}
