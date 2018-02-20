package org.karaffe.compiler.tree.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.names.PackageName;

public class PackageDef extends AbstractTree {
    private final PackageName packageName;
    private final List<ImportStatement> importStatements;
    private final List<TypeDefStatement> typeDefStatements;

    public PackageDef() {
        this(PackageName.ofRoot());
    }

    public PackageDef(PackageName packageName) {
        this(packageName, new ArrayList<>(), new ArrayList<>());
    }

    public PackageDef(PackageName packageName, ImportStatement... importStatements) {
        this(packageName, Arrays.asList(importStatements), new ArrayList<>());
    }

    public PackageDef(PackageName packageName, List<ImportStatement> importStatements, List<TypeDefStatement> typeDefStatements) {
        this.packageName = Objects.requireNonNull(packageName);
        this.importStatements = new ArrayList<>(Objects.requireNonNull(importStatements));
        this.typeDefStatements = new ArrayList<>(Objects.requireNonNull(typeDefStatements));
    }

    public PackageDef(Position position) {
        this(position, PackageName.ofRoot());
    }

    public PackageDef(Position position, PackageName packageName) {
        this(position, packageName, new ArrayList<>(), new ArrayList<>());
    }

    public PackageDef(Position position, PackageName packageName, ImportStatement... importStatements) {
        this(position, packageName, Arrays.asList(importStatements), new ArrayList<>());
    }

    public PackageDef(Position position, PackageName packageName, List<ImportStatement> importStatements, List<TypeDefStatement> typeDefStatements) {
        super(position);
        this.packageName = Objects.requireNonNull(packageName);
        this.importStatements = new ArrayList<>(Objects.requireNonNull(importStatements));
        this.typeDefStatements = new ArrayList<>(Objects.requireNonNull(typeDefStatements));
    }

    public <T extends ImportStatement> void addImportStatement(T importStatement) {
        this.importStatements.add(Objects.requireNonNull(importStatement));
    }

    public <T extends TypeDefStatement> void addTypeDefStatement(T typeDefStatement) {
        this.typeDefStatements.add(Objects.requireNonNull(typeDefStatement));
    }

    public PackageName getPackageName() {
        return new PackageName(this.packageName);
    }

    public List<? extends ImportStatement> getImports() {
        return new ArrayList<>(this.importStatements);
    }

    public List<? extends TypeDefStatement> getTypeDefStatements() {
        return new ArrayList<>(this.typeDefStatements);
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("package " + this.packageName + " {");
        this.importStatements.stream().map(ImportStatement::toString).forEach(lines::add);
        this.typeDefStatements.stream().map(TypeDefStatement::toString).forEach(lines::add);
        lines.add("}");
        return String.join("\n", lines);
    }
}
