package org.karaffe.compiler.tree.transform;

import org.karaffe.compiler.tree.transform.api.BaseDefaultTransformer;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.imports.AliasImport;
import org.karaffe.compiler.tree.v2.imports.OnDemandImport;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.util.NameGen;

public class NameResolver implements BaseDefaultTransformer {
    public static final String name = "name-resolver";
    private final NameGen nameGen = new NameGen("s_");

    @Override
    public CompilationUnit transform(CompilationUnit oldCompilationUnit) {
        return BaseDefaultTransformer.super.transform(oldCompilationUnit);
    }

    @Override
    public String getTransformerName() {
        return NameResolver.name;
    }

    @Override
    public void onSimpleImportAfter(SimpleImport simpleImport) {
        System.out.println(simpleImport);
    }

    @Override
    public void onAliasImportAfter(AliasImport aliasImport) {
        System.out.println(aliasImport);
    }

    @Override
    public void onOnDemandImportAfter(OnDemandImport onDemandImport) {
        System.out.println(onDemandImport);
    }

    @Override
    public void onLetLocalDefAfter(LetLocalDef letLocalDef) {
        System.out.println(letLocalDef);
    }

    @Override
    public void onBlockBefore(Block block) {
        System.out.println(">>> ");
    }

    @Override
    public void onBlockAfter(Block block) {
        System.out.println("<<< ");
    }
}
