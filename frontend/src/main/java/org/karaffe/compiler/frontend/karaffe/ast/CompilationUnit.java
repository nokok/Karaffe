package org.karaffe.compiler.frontend.karaffe.ast;

import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.ModuleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;
import org.karaffe.compiler.base.pos.Position;

import java.util.*;

public class CompilationUnit extends AbstractTree {

    private final Set<ModuleDef> modules;
    private final Set<PackageDef> packages;
    private final Set<TypeDefStatement> typeDefs;

    private final ModuleDef defaultUnnamedModule;
    private final PackageDef defaultUnnamedPackage;

    public CompilationUnit(CompilationUnit other) {
        this(other.getPosition(), other.getModules());
    }

    public CompilationUnit() {
        this(new ArrayList<>());
    }

    public CompilationUnit(ModuleDef... modules) {
        this(Arrays.asList(modules));
    }

    public CompilationUnit(Collection<? extends ModuleDef> modules) {
        this(Position.noPos(), null, null, modules);
    }

    public CompilationUnit(Position position, Collection<? extends ModuleDef> modules) {
        this(position, null, null, modules);
    }

    public CompilationUnit(Position position, ModuleDef defaultUnnamedModule, PackageDef defaultUnnamedPackage, Collection<? extends ModuleDef> modules) {
        super(position);
        this.modules = new LinkedHashSet<>(modules);
        this.packages = new HashSet<>();
        this.typeDefs = new HashSet<>();
        this.defaultUnnamedModule = Optional.ofNullable(defaultUnnamedModule).orElseGet(ModuleDef::rootModule);
        this.defaultUnnamedPackage = Optional.ofNullable(defaultUnnamedPackage).orElseGet(() -> new PackageDef(PackageName.ofRoot()));
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

    public ModuleDef getDefaultUnnamedModule() {
        return defaultUnnamedModule;
    }

    public PackageDef getDefaultUnnamedPackage() {
        return defaultUnnamedPackage;
    }

    public void addTypedefStatement(TypeDefStatement typeDefStatement) {
        this.defaultUnnamedPackage.addTypeDefStatement(typeDefStatement);
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
        if (this.defaultUnnamedModule != null && this.defaultUnnamedModule.hasChild()) {
            lines.add(this.defaultUnnamedModule.toString());
        }
        if (this.defaultUnnamedPackage != null && this.defaultUnnamedPackage.hasChild()) {
            lines.add(this.defaultUnnamedPackage.toString());
        }
        if (this.modules != null) {
            this.modules.stream().map(ModuleDef::toString).forEach(lines::add);
        }
        lines.add("}");
        return String.join("\n", lines);
    }
}
