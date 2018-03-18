package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.util.Traceable;

public class KNormalizerPhase extends AbstractCompileUnitTransformer implements Traceable {

    private final CompilerConfig config;

    public KNormalizerPhase(final CompilerConfig config) {
        this.config = config;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        final NormalizeContext context = new NormalizeContext();
        final NodeList normalized = input.normalize(context);
        final CompileUnit compileUnit = (CompileUnit) normalized.get(0);
        return Optional.of(compileUnit);
    }

}
