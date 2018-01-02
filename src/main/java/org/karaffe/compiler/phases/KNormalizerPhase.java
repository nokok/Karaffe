package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.il.KaraffeILs;
import org.karaffe.compiler.tree.CompileUnit;

public class KNormalizerPhase extends AbstractTransformer<CompileUnit, KaraffeILs> {

    public KNormalizerPhase() {
        super(CompileUnit.class, KaraffeILs.class);
    }

    @Override
    public Optional<KaraffeILs> transform(CompileUnit input) {
        return null;
    }

}
