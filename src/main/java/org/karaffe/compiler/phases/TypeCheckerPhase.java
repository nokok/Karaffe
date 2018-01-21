package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.tree.CompileUnit;

public class TypeCheckerPhase extends AbstractCompileUnitTransformer {

    @Override
    public Optional<CompileUnit> transform(CompileUnit input) {
        return Optional.of(input);
    }
}