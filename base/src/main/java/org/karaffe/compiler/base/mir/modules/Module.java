package org.karaffe.compiler.base.mir.modules;

import org.karaffe.compiler.base.mir.functions.Function;

import java.util.List;
import java.util.Objects;

public class Module {
    private String moduleName;
    private List<Function> functions;

    public Module(String moduleName) {
        this.moduleName = Objects.requireNonNull(moduleName);
    }

    public String getName() {
        return moduleName;
    }

    public void add(Function function) {
        this.functions.add(Objects.requireNonNull(function));
    }
}
