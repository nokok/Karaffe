package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Binding;
import org.karaffe.compiler.base.ir.util.KaraffeIRType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicBlock extends AbstractElement implements Block {

    private List<Binding> localVariables;
    private KaraffeIRType returnType;
    private Instructions instructions;

    public BasicBlock() {
        this.localVariables = new ArrayList<>();
    }

    @Override
    public List<Binding> getLocalVariables() {
        return this.localVariables;
    }

    @Override
    public KaraffeIRType getReturnType() {
        return returnType;
    }

    @Override
    public Instructions getInstructions() {
        return instructions;
    }

    @Override
    public void addLocalVariable(Binding binding) {
        this.localVariables.add(Objects.requireNonNull(binding));
    }

    @Override
    public void setReturnType(KaraffeIRType returnType) {
        this.returnType = Objects.requireNonNull(returnType);
    }

    @Override
    public void setInstructions(Instructions instructions) {
        this.instructions = Objects.requireNonNull(instructions);
    }

    @Override
    public void addInstruction(Instruction instruction) {
        this.instructions.add(Objects.requireNonNull(instruction));
    }
}
