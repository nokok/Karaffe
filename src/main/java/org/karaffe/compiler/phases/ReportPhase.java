package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.context.CompilerConfig;
import org.karaffe.compiler.tree.CompileUnit;

public class ReportPhase extends AbstractTransformer<CompileUnit, CompileUnit> {

    private final CompilerConfig context;

    public ReportPhase(final CompilerConfig context) {
        super(CompileUnit.class, CompileUnit.class);
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        return Optional.of(input);
    }

}
