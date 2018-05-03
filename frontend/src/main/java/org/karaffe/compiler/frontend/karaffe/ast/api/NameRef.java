package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;

import java.util.Optional;

public interface NameRef {

    default Optional<ExpressionName> asExprName() {
        return Optional.empty();
    }
}
