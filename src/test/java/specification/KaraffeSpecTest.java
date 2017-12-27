package specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.karaffe.compiler.lexer.AbstractLexer;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;

public class KaraffeSpecTest {

    /**
     * lineSeparator: LF
     */
    @Test
    public void testLineTerminatorLF() {
        AbstractLexer lexer = new KaraffeLexer("\n", false);
        List<Token> tokens = lexer.run();
        assertEquals(1, tokens.size());
        Token token = tokens.get(0);
        assertTrue(token.isNeedLineReset());
    }
}
