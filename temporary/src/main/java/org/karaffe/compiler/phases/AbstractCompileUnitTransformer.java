package org.karaffe.compiler.phases;

import org.karaffe.compiler.tree.CompileUnit;

public abstract class AbstractCompileUnitTransformer extends AbstractTransformer<CompileUnit, CompileUnit> {

    public AbstractCompileUnitTransformer() {
        super(CompileUnit.class, CompileUnit.class);
    }

}
