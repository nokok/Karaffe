package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Argument;
import org.karaffe.compiler.base.ir.util.KaraffeIRType;

import java.util.List;
import java.util.stream.Stream;

public interface Function extends Element {
    KaraffeIRType getReturnType();

    List<Argument> getArguments();

    List<Block> getBlocks();

    void setReturnType(KaraffeIRType returnType);

    void setArguments(List<Argument> arguments);

    void setBlocks(List<Block> blocks);

    void add(Block block);

    default Stream<Block> blockStream() {
        return this.getBlocks().stream();
    }

}
