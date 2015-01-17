/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class CastSyntaxTest {

    @Test
    public void testSimpleCastSyntax() {
        testCast("(1)~>Double");
    }

    @Test
    public void testNestedCast() {
        testCast("((1)~>Double)~>Int");
    }

    private void testCast(String code) {
        testCode("type D{"
                + "def d = "
                + code
                + "}");
    }
}
