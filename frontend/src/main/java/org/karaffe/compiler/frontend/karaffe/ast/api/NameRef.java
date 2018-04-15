package org.karaffe.compiler.frontend.karaffe.ast.api;

import java.util.Optional;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;

public interface NameRef {

    public default Optional<ExpressionName> asExprName() {
        return Optional.empty();
    }
}
