package org.karaffe.compiler.base.mir.transformer;

import org.karaffe.compiler.base.mir.InstructionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class TransformerConfig {

    private List<InstructionType> removeTypes = new ArrayList<>();

    public void addRemoveInstructionRule(InstructionType type) {
        this.removeTypes.add(Objects.requireNonNull(type));
    }

    public List<InstructionType> getRemoveTypes() {
        return removeTypes;
    }
}
