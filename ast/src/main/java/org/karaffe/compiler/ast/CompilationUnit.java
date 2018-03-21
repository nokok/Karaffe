package org.karaffe.compiler.ast;

import org.karaffe.compiler.ast.api.AbstractTree;
import org.karaffe.compiler.ast.names.ModuleName;
import org.karaffe.compiler.base.pos.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CompilationUnit extends AbstractTree {
    private final Set<ModuleDef> modules;

    public CompilationUnit(CompilationUnit other) {
        this(other.getPosition(), other.getModules());
    }

    public CompilationUnit() {
        this(new ArrayList<>());
    }

    public CompilationUnit(ModuleDef... modules) {
        this(Arrays.asList(modules));
    }

    public CompilationUnit(Position position, Collection<? extends ModuleDef> modules) {
        super(position);
        this.modules = new LinkedHashSet<>(modules);
    }

    public CompilationUnit(Collection<? extends ModuleDef> modules) {
        this.modules = new LinkedHashSet<>(modules);
    }

    public <T extends ModuleDef> void addModuleDef(T moduleDef) {
        Objects.requireNonNull(moduleDef);
        if (!this.modules.add(moduleDef)) {
            throw new IllegalStateException("duplicate modules");
        }
    }

    public List<? extends ModuleDef> getModules() {
        return new ArrayList<>(this.modules);
    }

    public CompilationUnit withModuleDef(ModuleDef moduleDef) {
        this.modules.add(Objects.requireNonNull(moduleDef));
        return this;
    }

    public CompilationUnit withPackageDef(ModuleName moduleName, PackageDef packageDef) {
        ModuleDef moduleDef = this
                .modules
                .stream()
                .filter(it -> it.getModuleName().equals(moduleName))
                .findFirst()
                .orElse(new ModuleDef(moduleName));
        moduleDef.addPackageDef(packageDef);
        this.modules.add(moduleDef);
        return this;
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("/* Compilation Unit */ {");
        this.modules.stream().map(ModuleDef::toString).forEach(lines::add);
        lines.add("}");

        return String.join("\n", lines);
    }
}
