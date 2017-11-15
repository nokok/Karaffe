package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.CompileUnit;
import org.karaffe.compiler.util.Traceable;

public class ParserPhase extends AbstractTransformer<Tokens, CompileUnit> implements Traceable {

    private final CompilerContext context;

    public ParserPhase(final CompilerContext context) {
        super(Tokens.class, CompileUnit.class);
        this.context = context;
        this.context.toString();
    }

    @Override
    public Optional<CompileUnit> transform(final Tokens input) {
        this.info("Parsing...");
        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse(input);
        final Optional<CompileUnit> r = result.getNode().map(CompileUnit.class::cast);
        this.info("Parse Completed.");
        return r;
    }

}
