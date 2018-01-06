package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.KNormalizer;

public class KNormalizerPhase extends AbstractCompileUnitTransformer {

    @Override
    public Optional<CompileUnit> transform(CompileUnit input) {
        KNormalizer normalizer = new KNormalizer();
        return Optional.of((CompileUnit) normalizer.normalize(input));
    }

}

