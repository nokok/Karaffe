package org.karaffe.compiler.tree.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.imports.OnDemandImport;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedName;
import org.karaffe.compiler.tree.v2.names.PackageName;
import org.karaffe.compiler.util.Report;

public class CompilationUnit extends AbstractTree {
    private final Set<PackageDef> packages;
    private final List<Report> reports = new ArrayList<>();
    public static Set<ImportStatement> defaultImports;

    static {
        Set<ImportStatement> imports = new LinkedHashSet<>();
        imports.add(new OnDemandImport(new PackageName("java", "lang")));
        imports.add(new OnDemandImport(new PackageName("java", "io")));
        imports.add(new OnDemandImport(new PackageName("java", "net")));
        imports.add(new OnDemandImport(new PackageName("java", "util")));
        imports.add(new OnDemandImport(new PackageName("java", "time")));
        imports.add(new OnDemandImport(new PackageName("java", "time", "chrono")));
        imports.add(new SimpleImport(new FullyQualifiedName("java", "time", "LocalDateTime")));
        imports.add(new SimpleImport(new FullyQualifiedName("java", "time", "chrono", "JapaneseEra")));
        imports.add(new OnDemandImport(new PackageName("karaffe", "core")));
        CompilationUnit.defaultImports = Collections.unmodifiableSet(imports);
    }

    public CompilationUnit() {
        this(new ArrayList<>());
    }

    public CompilationUnit(PackageDef... packages) {
        this(Arrays.asList(packages));
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

    public void addReport(Report report) {
        this.reports.add(Objects.requireNonNull(report));
    }

    public List<? extends Report> getReports() {
        return new ArrayList<>(this.reports);
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
