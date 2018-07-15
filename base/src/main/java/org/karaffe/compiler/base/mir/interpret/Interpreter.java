package org.karaffe.compiler.base.mir.interpret;

import org.karaffe.compiler.base.mir.Instruction;
import org.karaffe.compiler.base.mir.InstructionType;
import org.karaffe.compiler.base.mir.Instructions;
import org.karaffe.compiler.base.mir.block.ScopeInstruction;
import org.karaffe.compiler.base.mir.io.Load;
import org.karaffe.compiler.base.mir.util.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Interpreter implements Runnable {

    private final Instructions instructions;
    private final List<Consumer<Label>> scopeOpenListeners = new ArrayList<>();
    private final List<Consumer<Label>> scopeCloseListeners = new ArrayList<>();
    private final List<Consumer<Label>> onUseNameListeners = new ArrayList<>();

    public Interpreter(Instructions instructions) {
        this.instructions = instructions;
    }

    public void onScopeOpened(Consumer<Label> listener) {
        this.scopeOpenListeners.add(Objects.requireNonNull(listener));
    }

    public void onScopeClosed(Consumer<Label> listener) {
        this.scopeCloseListeners.add(Objects.requireNonNull(listener));
    }

    public void onUseName(Consumer<Label> listener) {
        this.onUseNameListeners.add(Objects.requireNonNull(listener));
    }

    public void onNewName(Consumer<String> listener) {
        
    }

    @Override
    public void run() {

        for (Instruction instruction : instructions) {
            if (instruction.getInstType() == InstructionType.BEGIN || instruction.getInstType() == InstructionType.END) {
                ScopeInstruction s = (ScopeInstruction) instruction;
                Label scopeName = s.getScopeName();
                if (instruction.getInstType() == InstructionType.BEGIN) {
                    scopeOpenListeners.forEach(l -> l.accept(scopeName));
                } else {
                    scopeCloseListeners.forEach(l -> l.accept(scopeName));
                }
            } else if (instruction.getInstType() == InstructionType.LOAD) {
                Load l = (Load) instruction;
                onUseNameListeners.forEach(listener -> listener.accept(l.getLoadName()));
            } else if (instruction.getInstType() == InstructionType.STORE) {

            }
        }
    }
}
