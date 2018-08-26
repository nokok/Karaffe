package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Argument;
import org.karaffe.compiler.base.ir.util.KaraffeIRType;

import java.util.List;
import java.util.Objects;

public class DefaultFunction extends AbstractElement implements Function {

    private String functionName;
    private KaraffeIRType returnType;
    private List<Argument> arguments;
    private List<Block> blocks;

    @Override
    public String getFunctionName() {
        return this.functionName;
    }

    @Override
    public KaraffeIRType getReturnType() {
        return this.returnType;
    }

    @Override
    public List<Argument> getArguments() {
        return this.arguments;
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public void setFunctionName(String name) {
        this.functionName = Objects.requireNonNull(name);
    }

    @Override
    public void setReturnType(KaraffeIRType returnType) {
        this.returnType = Objects.requireNonNull(returnType);
    }

    @Override
    public void setArguments(List<Argument> arguments) {
        this.arguments = Objects.requireNonNull(arguments);
    }

    @Override
    public void setBlocks(List<Block> blocks) {
        this.blocks = Objects.requireNonNull(blocks);
    }

    @Override
    public void add(Block block) {
        this.blocks.add(Objects.requireNonNull(block));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("function ").append(this.functionName).append(" {").append(System.lineSeparator());
        for (Block block : blocks) {
            sb.append("  ").append(block).append(System.lineSeparator());
        }
        sb.append("}").append(System.lineSeparator());
        return sb.toString();
    }
}
