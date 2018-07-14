package org.karaffe.compiler.mir.io;

import org.karaffe.compiler.mir.AbstractInstruction;
import org.karaffe.compiler.mir.util.Label;

import java.util.Objects;

public class Load extends AbstractInstruction {
    private Label loadName;

    public Load(Label loadName) {
        this.loadName = Objects.requireNonNull(loadName);
    }

    @Override
    public String toString() {
        return "Load " + loadName;
    }
}
