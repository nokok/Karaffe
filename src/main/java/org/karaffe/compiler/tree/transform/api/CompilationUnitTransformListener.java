package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.CompilationUnit;

public interface CompilationUnitTransformListener {

    public default void onCompilationUnitBefore(CompilationUnit compilationUnit) {

    }

    public default void onCompilationUnitAfter(CompilationUnit compilationUnit) {

    }
}