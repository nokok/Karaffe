package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;

public interface CompilationUnitTransformer extends ModuleDefTransformer {

    public default void onCompilationUnitBefore(CompilationUnit compilationUnit) {

    }

    public default void onCompilationUnitAfter(CompilationUnit compilationUnit) {

    }

    public default CompilationUnit transformBody(CompilationUnit oldCompilationUnit) {
        return oldCompilationUnit; //TODO
    }

    @Override
    public default CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        onCompilationUnitBefore(oldCompilationUnit);
        CompilationUnit compilationUnit = transformBody(oldCompilationUnit);
        onCompilationUnitAfter(compilationUnit);
        return compilationUnit;
    }

}
