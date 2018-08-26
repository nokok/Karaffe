package org.karaffe.compiler.base.ir;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DefaultModule extends AbstractElement implements Module {

    private final String moduleName;
    private final List<Function> functions;

    public DefaultModule(String moduleName) {
        this.moduleName = moduleName;
        this.functions = new ArrayList<>();
    }

    @Override
    public String getModuleName() {
        return this.moduleName;
    }

    @Override
    public void add(Function function) {
        this.functions.add(Objects.requireNonNull(function));
    }

    @Override
    public void addFirst(Function function) {
        this.functions.add(0, Objects.requireNonNull(function));
    }

    @Override
    public List<Function> getFunctions() {
        return this.functions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("module ").append(this.moduleName).append(" {").append(System.lineSeparator());
        for (Function function : functions) {
            sb.append("  ").append(function).append(System.lineSeparator());
        }
        sb.append("}").append(System.lineSeparator());
        return sb.toString();
    }
}
