package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.mir.functions.Function;
import org.karaffe.compiler.base.mir.modules.Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntermediateRepresentation {
    private List<Module> modules;

    public IntermediateRepresentation() {
        this.modules = new ArrayList<>();
    }

    public void add(Module module) {
        this.modules.add(Objects.requireNonNull(module));
    }

    public void add(Function function) {
        if (this.modules.size() != 1) {
            throw new IllegalStateException();
        }
        this.modules.get(0).add(function);
    }

    public void add(String moduleName, Function function) {
        this.modules.stream().filter(m -> m.getName().equals(moduleName)).findFirst().ifPresent(m -> m.add(function));
    }

    public List<Module> getModules() {
        return this.modules;
    }
}
