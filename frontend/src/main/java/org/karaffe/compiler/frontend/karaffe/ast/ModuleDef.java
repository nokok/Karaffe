package org.karaffe.compiler.frontend.karaffe.ast;

import org.karaffe.compiler.frontend.karaffe.ast.api.ModuleDirective;
import org.karaffe.compiler.frontend.karaffe.ast.names.ModuleName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleDef {
    private final ModuleName moduleName;
    private final List<PackageDef> packages;
    private final List<ModuleDirective> directives;

    private boolean isOpenModule = false;

    public ModuleDef(ModuleName moduleName) {
        this(moduleName,
                new ArrayList<>(0),
                new ArrayList<>(0));
    }

    public ModuleDef(String moduleName) {
        this(new ModuleName(moduleName),
                new ArrayList<>(0),
                new ArrayList<>(0));
    }

    public ModuleDef(
            ModuleName moduleName,
            List<? extends PackageDef> packages,
            List<? extends ModuleDirective> directives) {
        this.moduleName = Objects.requireNonNull(moduleName);
        this.packages = new ArrayList<>(packages);
        this.directives = new ArrayList<>(directives);
    }

    public void addPackageDef(PackageDef packageDef) {
        this.packages.add(Objects.requireNonNull(packageDef));
    }

    public void addDirective(ModuleDirective moduleDirective) {
        this.directives.add(Objects.requireNonNull(moduleDirective));
    }

    public void setOpenModule() {
        this.isOpenModule = true;
    }

    public void unsetOpenModule() {
        this.isOpenModule = false;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public List<PackageDef> getPackages() {
        return new ArrayList<>(packages);
    }

    public List<ModuleDirective> getDirectives() {
        return new ArrayList<>(directives);
    }

    public boolean hasChild() {
        return !(this.packages.isEmpty() && this.directives.isEmpty());
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
        lines.add(String.format("%smodule %s {", this.isOpenModule ? "open " : "", this.moduleName));
        this.directives.stream().map(ModuleDirective::toString).forEach(lines::add);
        this.packages.stream().map(pkg -> pkg.toString()).forEach(lines::add);
        lines.add("}");
        return String.join("\n", lines);
    }
}
