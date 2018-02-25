package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;

public interface BaseDefaultTransformer
        extends TypeDefStmtTransformer,
        ExpressionTransformer,
        ImportStatementTransformer,
        StatementTransformer,
        CompilationUnitTransformListener,
        PackageDefTransformListener {

    @Override
    public default CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        onCompilationUnitBefore(oldCompilationUnit);
        CompilationUnit compilationUnit = new CompilationUnit();
        oldCompilationUnit.getPackages().forEach(pkg -> {
            PackageDef packageDef = transform(pkg);
            compilationUnit.addPackageDef(packageDef);
        });
        onCompilationUnitAfter(compilationUnit);
        return compilationUnit;
    }

    @Override
    public default PackageDef transform(PackageDef oldPackageDef) {
        onPackageDefBefore(oldPackageDef);
        PackageDef packageDef = new PackageDef(oldPackageDef.getPackageName());
        for (ImportStatement importStatement : oldPackageDef.getImports()) {
            if (!packageDef.hasImport(importStatement)) {
                packageDef.addImportStatement(transform(importStatement));
            }
        }
        for (TypeDefStatement typeDefStatement : oldPackageDef.getTypeDefStatements()) {
            packageDef.addTypeDefStatement(transform(typeDefStatement));
        }
        onPackageDefAfter(packageDef);
        return packageDef;
    }

}
