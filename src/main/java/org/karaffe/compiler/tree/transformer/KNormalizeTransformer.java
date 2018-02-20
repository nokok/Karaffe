package org.karaffe.compiler.tree.transformer;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.names.PackageName;

public class KNormalizeTransformer {
    public CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        CompilationUnit compilationUnit = new CompilationUnit();
        List<? extends PackageDef> packages = compilationUnit.getPackages();
        for (PackageDef pkg : packages) {
            PackageName packageName = pkg.getPackageName();
            PackageDef packageDef = new PackageDef(packageName);
            pkg.getImports().stream().forEach(packageDef::addImportStatement);
            List<TypeDefStatement> statements = new ArrayList<>();
            for (TypeDefStatement typeDefStatement : pkg.getTypeDefStatements()) {
                if (typeDefStatement.isInterfaceDecl()) {
                    statements.add(typeDefStatement);
                    continue;
                } else if (typeDefStatement.isClassDecl()) {

                }
                throw new IllegalStateException("isInterfaceDecl() == false && isClassDecl() == false");
            }
        }
        return compilationUnit;
    }
}
