package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.mir.instructions.util.Label;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Deprecated
public interface DeprecatedInstructions extends List<DeprecatedInstruction> {
    void updateInternalCache();

    default Optional<DeprecatedInstruction> getElementByLabel(Label label) {
        List<DeprecatedInstruction> instructionsByLabel = this.getInstructionsByLabel(label);
        if (instructionsByLabel.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(instructionsByLabel.get(0));
    }

    default List<DeprecatedInstruction> getInstructionsByLabel(Label label) {
        return this.stream()
                .filter(i -> i instanceof LabeledInstruction)
                .map(LabeledInstruction.class::cast)
                .filter(f -> {
                    Label iLabel = f.getLabel();
                    return iLabel.equals(label);
                })
                .map(DeprecatedInstruction.class::cast)
                .collect(Collectors.toList());
    }
}