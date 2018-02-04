package org.karaffe.compiler.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.tree.Name;

public class MayBeApplicable extends Undecidable {
    private final InferResult ownerType;
    private final Name member;

    public MayBeApplicable(InferResult ownerType, Name member) {
        this.ownerType = ownerType;
        this.member = member;
    }

    public InferResult getOwnerType() {
        return this.ownerType;
    }

    public Name getMemberName() {
        return this.member;
    }

    @Override
    public String toString() {
        return this.ownerType.getType().orElse("???") + "#" + this.member.getText();
    }

    @Override
    public ResultType getInferResultType() {
        return ResultType.MAY_BE_APPLICABLE;
    }

    @Override
    public Optional<InferResult> compose(InferResult other, TypeInferenceContext context) {
        return Optional.of(InferResult.applying(this, new ArrayList<>(Arrays.asList(other))));
    }
}
