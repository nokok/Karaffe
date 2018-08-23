package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ir extends List<Instruction> {
    void updateInternalCache();

    default Optional<Instruction> getElementByLabel(Label label) {
        List<Instruction> instructionsByLabel = this.getInstructionsByLabel(label);
        if (instructionsByLabel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(instructionsByLabel.get(0));
    }

    default List<Instruction> getInstructionsByLabel(Label label) {
        return this.stream()
                .filter(i -> i instanceof LabeledInstruction)
                .map(LabeledInstruction.class::cast)
                .filter(f -> {
                    Label iLabel = f.getLabel();
                    return iLabel.equals(label);
                })
                .map(Instruction.class::cast)
                .collect(Collectors.toList());
    }
}