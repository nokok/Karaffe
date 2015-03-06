/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
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
