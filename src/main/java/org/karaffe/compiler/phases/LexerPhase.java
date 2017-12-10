package org.karaffe.compiler.phases;

import java.util.Optional;

import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Tokens;

public class LexerPhase extends AbstractTransformer<String, Tokens> {

    public LexerPhase() {
        super(String.class, Tokens.class);
    }

    @Override
    public Optional<Tokens> transform(String input) {
        KaraffeLexer lexer = new KaraffeLexer(input);
        return Optional.of(new Tokens(lexer.run()));
    }
}
