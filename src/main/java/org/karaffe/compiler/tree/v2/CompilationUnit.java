package org.karaffe.compiler.tree.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.TreeVisitor;

public class CompilationUnit extends AbstractTree {
    private final List<PackageDef> packages;

    public CompilationUnit() {
        this(new ArrayList<>());
    }

    public CompilationUnit(PackageDef... packages) {
        this(Arrays.asList(packages));
    }

    public CompilationUnit(List<? extends PackageDef> packages) {
        this.packages = new ArrayList<>(packages);
    }

    public <T extends PackageDef> void addPackageDef(T packageDef) {
        this.packages.add(packageDef);
    }

    public List<? extends PackageDef> getPackages() {
        return new ArrayList<>(this.packages);
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("/* Compilation Unit */ {");
        this.packages.stream().map(pkg -> pkg.toString()).forEach(lines::add);
        lines.add("}");

        return String.join("\n", lines);
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);
    }
}
