package org.karaffe.compiler.base.ir;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DefaultIR implements IR {

    private List<Element> elements;
    private List<Module> modules;
    private List<Block> blocks;

    DefaultIR() {
        this.elements = new ArrayList<>();
        this.modules = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    @Override
    public void add(Module module) {
        this.elements.add(Objects.requireNonNull(module));
        this.modules.add(Objects.requireNonNull(module));
    }

    @Override
    public void add(Function function) {
        this.modules.get(0).add(Objects.requireNonNull(function));
    }

    @Override
    public void add(Module module, Function function) {
        this.modules.get(this.modules.indexOf(module)).add(function);
    }

    @Override
    public void add(String moduleName, Function function) {
        this.modules.stream()
                .filter(m -> m.getModuleName().equals(moduleName))
                .findFirst()
                .ifPresent(m -> m.add(Objects.requireNonNull(function)));
    }

    @Override
    public void add(Block block) {
        this.elements.add(Objects.requireNonNull(block));
        this.blocks.add(Objects.requireNonNull(block));
    }

    @Override
    public Stream<Module> moduleStream() {
        return this.modules.stream();
    }

    @Override
    public Stream<Block> globalBlockStream() {
        return this.blocks.stream();
    }

    @Override
    public List<Module> getModules() {
        return this.modules;
    }

    @Override
    public List<Function> getFunctions() {
        return this.getModules().stream().flatMap(Module::functionStream).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Element element : elements) {
            sb.append(element).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
