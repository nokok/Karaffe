package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.phases.knormalize.KNormalizeVisitor;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.Traceable;

public class KNormalizerPhase extends AbstractCompileUnitTransformer implements Traceable {

    private final CompilerConfig config;

    public KNormalizerPhase(CompilerConfig config) {
        this.config = config;
    }

    @Override
    public Optional<CompileUnit> transform(CompileUnit input) {
        KNormalizeVisitor visitor = new KNormalizeVisitor(input);
        Node node = visitor.normalize();
        CompileUnit normalizedCompileUnit = (CompileUnit) node;
        return Optional.ofNullable(normalizedCompileUnit);
    }

}
