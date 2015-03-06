/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class IfExprTest {

    @Test
    public void testIfExpr() {
        testExpr("if(true){}else{}");
    }

    @Test
    public void testIfThenElse() {
        testExpr("if(true){}else if(true){}else{}");
    }

    @Test
    public void testIfExprWithoutBrase() {
        testExpr("def a = if(true) false else true");
    }

    private void testExpr(String code) {
        testCode("type D{\n"
                + "func this() {\n"
                + code
                + "}\n"
                + "}");
    }
}
