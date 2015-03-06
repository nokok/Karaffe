/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
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
