package org.karaffe.compiler.backend.transformer.api;

import org.karaffe.compiler.ast.CompilationUnit;

import java.util.stream.Collectors;

public interface CompilationUnitTransformer extends ModuleDefTransformer {

    public default void onCompilationUnitBefore(CompilationUnit compilationUnit) {

    }

    public default void onCompilationUnitAfter(CompilationUnit compilationUnit) {

    }

    public default CompilationUnit transformBody(CompilationUnit oldCompilationUnit) {
        return new CompilationUnit(
                oldCompilationUnit.getPosition(),
                transform(oldCompilationUnit.getDefaultUnnamedModule()),
                transform(oldCompilationUnit.getDefaultUnnamedPackage()),
                oldCompilationUnit.getModules().stream().map(this::transform).collect(Collectors.toList())
        );
    }

    @Override
    public default CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        onCompilationUnitBefore(oldCompilationUnit);
        CompilationUnit compilationUnit = transformBody(oldCompilationUnit);
        onCompilationUnitAfter(compilationUnit);
        return compilationUnit;
    }

}
