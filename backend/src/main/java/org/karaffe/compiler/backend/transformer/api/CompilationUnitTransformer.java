package org.karaffe.compiler.backend.transformer.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.CompilationUnit;

public interface CompilationUnitTransformer extends BaseTransformer {

    public default void onCompilationUnitBefore(CompilationUnit compilationUnit) {

    }

    public default void onCompilationUnitAfter(CompilationUnit compilationUnit) {

    }

    public default CompilationUnit transformBody(CompilationUnit oldCompilationUnit) {
        return new CompilationUnit(
                oldCompilationUnit.getPosition(),
                oldCompilationUnit.getPackages().stream().map(this::transform).collect(Collectors.toList()));
    }

    @Override
    public default CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        onCompilationUnitBefore(oldCompilationUnit);
        CompilationUnit compilationUnit = transformBody(oldCompilationUnit);
        onCompilationUnitAfter(compilationUnit);
        return compilationUnit;
    }

}
