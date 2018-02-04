package org.karaffe.compiler.types;

import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.Name;

public class AnyTarget extends Undecidable {

    private final Name name;

    public AnyTarget(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "?#" + this.name.getText();
    }

    public Name getName() {
        return this.name;
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.ANY_TARGET;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context) {
        return Optional.empty();
    }

}
