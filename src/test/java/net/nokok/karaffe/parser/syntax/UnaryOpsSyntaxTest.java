/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class UnaryOpsSyntaxTest {

    @Test
    public void testSimpleUnaryOperatorTest() {
        testExpr("p(-1)");
    }

    @Test
    public void testUnaryOperatorTest() {
        testExpr("p(- - -1)");
    }

    public static void testExpr(String code) {
        testCode("type D{\n"
                + "def a = \n"
                + code
                + "}");
    }

}
