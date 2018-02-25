package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.expressions.Block;

public interface BlockTransformer extends BaseTransformer {

    public default void onBlockBefore(Block block) {

    }

    public default void onBlockAfter(Block block) {

    }

    public default Block transform(Block oldBlock) {
        onBlockBefore(oldBlock);
        Block block = new Block();
        for (Statement stmt : oldBlock.getBody()) {
            Statement transformed = transform(stmt);
            block.add(transformed);
        }
        return block;
    }

}
