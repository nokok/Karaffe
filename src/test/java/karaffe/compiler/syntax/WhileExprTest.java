/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
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
