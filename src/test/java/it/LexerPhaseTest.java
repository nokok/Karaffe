package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.phases.LexerPhase;

public class LexerPhaseTest {
    @Test
    public void testLexer() {
        final LexerPhase lexerPhase = new LexerPhase();
        final Optional<Tokens> tokenOpt = lexerPhase.transform("1");
        assertTrue(tokenOpt.isPresent());
        assertEquals(LiteralToken.IntLiteral.class, tokenOpt.get().iterator().next().getClass());
    }

    @Test
    public void testLexer2() {
        final LexerPhase lexerPhase = new LexerPhase();
        final Optional<Tokens> tokenOpt = lexerPhase.transform("");
        assertTrue(tokenOpt.isPresent());
        assertEquals(CommonToken.EOF.class, tokenOpt.get().iterator().next().getClass());
    }

}
