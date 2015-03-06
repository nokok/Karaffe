/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class SwitchExprTest {

    @Test
    public void testSimpleSwitchExpr() {
        testSwitch("match (2){\n"
                + "case x Int => println(x)"
                + "}");
    }

    @Test
    public void testGuard() {
        testSwitch("match (expr) {\n"
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
