package org.karaffe.compiler.base.ir;

import org.karaffe.compiler.base.ir.util.Binding;
import org.karaffe.compiler.base.ir.util.KaraffeIRType;

import java.util.List;
import java.util.stream.Stream;

public interface Block extends Element {
    List<Binding> getLocalVariables();

    KaraffeIRType getReturnType();

    Instructions getInstructions();

    default Stream<Instruction> instructionStream() {
        return getInstructions().stream();
    }

    void addLocalVariable(Binding binding);

    void setReturnType(KaraffeIRType returnType);

    void setInstructions(Instructions instructions);

    void addInstruction(Instruction instruction);

}
