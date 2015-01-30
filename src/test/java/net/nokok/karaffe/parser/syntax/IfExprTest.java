/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
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

    private void testExpr(String code) {
        testCode("type D{\n"
                + "func this() {\n"
                + code
                + "}\n"
                + "}");
    }
}
