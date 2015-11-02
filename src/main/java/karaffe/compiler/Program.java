package karaffe.compiler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import org.objectweb.asm.tree.ClassNode;

public class Program {

    private final PackageDef packageDef;
    private final List<ImportDef> importDefList;
    private final List<ClassDef> classDefList;

    Program(PackageDef packageDef, List<ImportDef> importDefList, List<ClassDef> c) {
        this.packageDef = packageDef;
        this.importDefList = Objects.requireNonNull(importDefList);
        this.classDefList = Objects.requireNonNull(c);
    }

    public Optional<PackageDef> packageDef() {
        return Optional.of(packageDef);
    }

    public List<ImportDef> importDefList() {
        return Collections.unmodifiableList(importDefList);
    }

    public List<ClassDef> classDefList() {
        return Collections.unmodifiableList(classDefList);
    }

    public PackageDef getPackageDef() {
        return packageDef;
    }

    public List<ImportDef> getImportDefList() {
        return importDefList;
    }

    public List<ClassDef> getClassDefList() {
        return classDefList;
    }

    @Override
    public String toString() {
        return "(program " + String.join(" ", packageDef.toString(), importDefList.toString(), classDefList.toString()) + ")";
    }

    public List<ClassNode> toClassNodes() {
        return classDefList.stream().map(c -> c.toNode()).collect(toList());
    }
}
