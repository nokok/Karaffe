package org.karaffe.compiler.tree.transform.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.tree.v2.expressions.Block;

public interface BlockTransformer extends BaseTransformer {

    public default void onBlockBefore(Block block) {

    }

    public default void onBlockAfter(Block block) {

    }

    public default Block transformBody(Block oldBlock) {
        return new Block(
                oldBlock.getPosition(),
                oldBlock.getBody().stream().map(this::transform).collect(Collectors.toList()));
    }

    public default Block transform(Block oldBlock) {
        onBlockBefore(oldBlock);
        Block block = transformBody(oldBlock);
        onBlockAfter(block);
        return block;
    }

}
