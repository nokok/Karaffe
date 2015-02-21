/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class WhileExprTest {

    @Test
    public void testWhileExpr() {
        testExpr("while (true) {}");
    }

    @Test(expected = AssertionError.class)
    public void testWhileExprEmptyCondition() {
        testExpr("while {}");
    }

    @Test(expected = AssertionError.class)
    public void testWhileExprEmptyBlock() {
        testExpr("while()");
    }

    private void testExpr(String code) {
        testCode("type D{\n"
                + "func this() {\n"
                + code
                + "\n}"
                + "}");
    }
}
