package org.karaffe.compiler.base.mir.instructions.util;

import org.karaffe.compiler.base.mir.instructions.DeprecatedInstruction;
import org.karaffe.compiler.base.mir.instructions.InstructionType;
import org.karaffe.compiler.base.mir.instructions.DeprecatedInstructions;
import org.karaffe.compiler.base.mir.instructions.block.BeginClass;
import org.karaffe.compiler.base.mir.instructions.block.BeginMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstructionList extends ArrayList<DeprecatedInstruction> implements DeprecatedInstructions {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstructionList.class);
    private Map<Label, DeprecatedInstructions> classes = new HashMap<>();
    private Map<Label, DeprecatedInstructions> methods = new HashMap<>();

    @Override
    public void updateInternalCache() {
        LOGGER.trace("Updating Internal cache");
        int beginClass = 0;
        Label classLabel = null;
        int beginMethod = 0;
        Label methodLabel = null;
        this.classes = new HashMap<>();
        this.methods = new HashMap<>();
        for (int index = 0; index < this.size(); index++) {
            DeprecatedInstruction instruction = this.get(index);
            if (instruction.getInstType() == InstructionType.BEGINCLASS) {
                beginClass = index;
                classLabel = ((BeginClass) instruction).getLabel();
            }
            if (instruction.getInstType() == InstructionType.BEGINMETHOD) {
                beginMethod = index;
                methodLabel = ((BeginMethod) instruction).getLabel();
            }
            if (instruction.getInstType() == InstructionType.ENDCLASS) {
                List<DeprecatedInstruction> instructions = this.subList(beginClass, index);
                DeprecatedInstructions i = new InstructionList();
                i.addAll(instructions);
                this.classes.put(classLabel, i);
            }
            if (instruction.getInstType() == InstructionType.ENDMETHOD) {
                List<DeprecatedInstruction> instructions = this.subList(beginMethod, index);
                DeprecatedInstructions i = new InstructionList();
                i.addAll(instructions);
                this.methods.put(methodLabel, i);
            }
        }
        LOGGER.trace("Internal cache updated");
    }

    @Override
    public String toString() {
        return String.join("\n", this
                .stream()
                .map(i ->
                        String.format("[%15s]%s %s",
                                i.getPosition().toStringNoSource(),
                                i.hasAttribute() ? " " + i.getAttributes() : "",
                                i))
                .collect(Collectors.toList()));
    }
}