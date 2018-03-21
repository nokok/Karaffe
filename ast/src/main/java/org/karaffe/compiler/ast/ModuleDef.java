package org.karaffe.compiler.ast;

import org.karaffe.compiler.ast.names.ModuleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleDef {
    private final ModuleName moduleName;
    private final List<PackageDef> packages;
    private final List<ModuleName> exports;
    private final List<ModuleName> requires;

    public ModuleDef(ModuleName moduleName) {
        this(moduleName,
                new ArrayList<>(0),
                new ArrayList<>(0),
                new ArrayList<>(0));
    }

    public ModuleDef(String moduleName) {
        this(new ModuleName(moduleName),
                new ArrayList<>(0),
                new ArrayList<>(0),
                new ArrayList<>(0));
    }

    public ModuleDef(
            ModuleName moduleName,
            List<? extends PackageDef> packages,
            List<? extends ModuleName> exports,
            List<? extends ModuleName> requires) {
        this.moduleName = Objects.requireNonNull(moduleName);
        this.packages = new ArrayList<>(packages);
        this.exports = new ArrayList<>(exports);
        this.requires = new ArrayList<>(requires);
    }

    public void addPackageDef(PackageDef packageDef) {
        this.packages.add(Objects.requireNonNull(packageDef));
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public List<PackageDef> getPackages() {
        return new ArrayList<>(packages);
    }

    public List<ModuleName> getExports() {
        return new ArrayList<>(exports);
    }

    public List<ModuleName> getRequires() {
        return new ArrayList<>(requires);
    }

    public static ModuleName rootModuleName() {
        return new ModuleName("<root>");
    }

    public static ModuleDef rootModule() {
        return new ModuleDef(ModuleDef.rootModuleName());
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add(String.format("module %s {", this.moduleName));
        this.exports.stream().map(exports -> String.format("exports %s;", exports)).forEach(lines::add);
        this.requires.stream().map(requires -> String.format("requires %s;", requires)).forEach(lines::add);
        this.packages.stream().map(pkg -> pkg.toString()).forEach(lines::add);
        lines.add("}");
        return String.join("\n", lines);
    }
}
