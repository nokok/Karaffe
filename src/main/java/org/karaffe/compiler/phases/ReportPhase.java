package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.tree.CompileUnit;

public class ReportPhase extends AbstractTransformer<CompileUnit, CompileUnit> {

    private final CompilerContext context;

    public ReportPhase(final CompilerContext context) {
        super(CompileUnit.class, CompileUnit.class);
        this.context = context;
        this.context.toString();
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        return Optional.of(input);
    }

}
