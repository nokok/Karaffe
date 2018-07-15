package org.karaffe.compiler.base.mir.util.attr;

import org.karaffe.compiler.base.mir.block.BlockType;

import java.util.Objects;

public class BlockTypeAttribute extends Attribute {
    private BlockType blockType;

    public BlockTypeAttribute(BlockType blockType) {
        this.blockType = Objects.requireNonNull(blockType);
    }

    public BlockType getBlockType() {
        return blockType;
    }

    @Override
    public String toString() {
        return blockType.name();
    }
}
