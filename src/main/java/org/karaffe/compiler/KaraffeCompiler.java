package org.karaffe.compiler;

import java.util.concurrent.Callable;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedName;
import org.karaffe.compiler.tree.v2.names.PackageName;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class KaraffeCompiler implements Callable<Integer> {

    private final CompilerConfig config;

    public KaraffeCompiler(CompilerConfig config) {
        this.config = config;
    }

    @Override
    public Integer call() throws Exception {
        CompilationUnit compilationUnit = new CompilationUnit();

        compilationUnit.addPackageDef(new PackageDef(
                new PackageName(new SimpleName("foo")),
                new SimpleImport(
                        new FullyQualifiedName(
                                new SimpleName("")))));

        return -1;
    }

}
