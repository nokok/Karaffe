package org.karaffe.compiler.tree.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class CompilationUnit extends AbstractTree {
    private final Set<PackageDef> packages;

    public CompilationUnit(CompilationUnit other) {
        this(other.getPosition(), other.getPackages());
    }

    public CompilationUnit() {
        this(new ArrayList<>());
    }

    public CompilationUnit(PackageDef... packages) {
        this(Arrays.asList(packages));
    }

    public CompilationUnit(Position position, Collection<? extends PackageDef> packages) {
        super(position);
        this.packages = new LinkedHashSet<>(packages);
    }

    public CompilationUnit(Collection<? extends PackageDef> packages) {
        this.packages = new LinkedHashSet<>(packages);
    }

    public <T extends PackageDef> void addPackageDef(T packageDef) {
        Objects.requireNonNull(packageDef);
        if (!this.packages.add(packageDef)) {
            throw new IllegalStateException("duplicate packages");
        }
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
}
