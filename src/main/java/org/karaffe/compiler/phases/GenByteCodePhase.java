package org.karaffe.compiler.phases;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.Traceable;

public class GenByteCodePhase extends AbstractTransformer<CompileUnit, CompileUnit> implements Traceable {

    private final CompilerContext context;

    public GenByteCodePhase(final CompilerContext context) {
        super(CompileUnit.class, CompileUnit.class);
        this.context = context;
    }

    @Override
    public Optional<CompileUnit> transform(final CompileUnit input) {
        return Optional.of(input);
    }

    private String printChildren(final List<? extends Node> children) {
        final StringBuilder builder = new StringBuilder();
        for (final Node child : children) {
            builder.append(this.printChildren(child.getChildren())).append(System.lineSeparator());
            builder.append(child.getNodeType());
        }
        return builder.toString();
    }
}
