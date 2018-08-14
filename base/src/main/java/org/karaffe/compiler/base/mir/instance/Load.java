package org.karaffe.compiler.base.mir.instance;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.Objects;

public class Load extends AbstractInstruction {
    private Label loadName;

    public Load(Label loadName) {
        this.loadName = Objects.requireNonNull(loadName);
    }

    public Label getLoadName() {
        return loadName;
    }

    @Override
    public String toString() {
        return "Load " + loadName;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.LOAD;
    }
}
