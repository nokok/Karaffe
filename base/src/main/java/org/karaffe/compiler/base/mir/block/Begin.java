package org.karaffe.compiler.base.mir.block;

import org.karaffe.compiler.base.mir.AbstractInstruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.util.Label;
import org.karaffe.compiler.base.mir.util.attr.BlockTypeAttribute;

import java.util.Objects;

public class Begin extends AbstractInstruction implements ScopeInstruction {
    private Label label;
    private BlockType blockType;

    public Begin(BlockType type, Label label) {
        this.label = Objects.requireNonNull(label);
        this.addAttribute(new BlockTypeAttribute(type));
        this.blockType = type;
    }

    public Label getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Begin " + label;
    }

    @Override
    public InstructionType getInstType() {
        return InstructionType.BEGIN;
    }

    @Override
    public Label getScopeName() {
        return label;
    }

    public BlockType getBlockType() {
        return this.blockType;
    }
}
