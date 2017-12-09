package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Traceable;

public class GenByteCodePhase extends AbstractTransformer<CompileUnit, CompileUnit> implements Traceable {

    private final CompilerConfig context;

    public GenByteCodePhase(final CompilerConfig context) {
        super(CompileUnit.class, CompileUnit.class);
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        return Optional.of(input);
    }

}
