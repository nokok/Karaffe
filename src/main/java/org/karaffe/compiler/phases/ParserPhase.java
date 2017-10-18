package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.CompilerContext;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.CompileUnit;

public class ParserPhase extends AbstractTransformer<Tokens, CompileUnit> {

    private final CompilerContext context;

    public ParserPhase(final CompilerContext context) {
        super(Tokens.class, CompileUnit.class);
        this.context = context;
        this.context.toString();
    }

    @Override
    public Optional<CompileUnit> transform(final Tokens input) {
        final KaraffeParser parser = new KaraffeParser();
        final MatchResult result = parser.parse(input);
        return result.getNode().map(CompileUnit.class::cast);
    }

}
