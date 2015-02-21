/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class SwitchExprTest {

    @Test
    public void testSimpleSwitchExpr() {
        testSwitch("switch (2){\n"
                + "case x Int => println(x)"
                + "}");
    }

    @Test
    public void testGuard() {
        testSwitch("switch (expr) {\n"
                + "case x Int @ x > 0 => println(x)\n"
                + "}");
    }

    private void testSwitch(String code) {
        testCode("type D{\n"
                + "func this() {\n"
                + code
                + "}\n"
                + "}");
    }
}
