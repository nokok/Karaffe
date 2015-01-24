/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.devel;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class SwitchExprTest {

    @Test
    public void testSimpleSwitchExpr() {
        testSwitch("def a = switch (cond) {\n"
                + "case 1 => \"One\"\n"
                + "case 2 => \"Two\"\n"
                + "case 3:Int => \"Three\""
                + "}\n");
    }

    private void testSwitch(String code) {
        testCode("type D{\n"
                + "def this() = {\n"
                + code
                + "}\n"
                + "}");
    }
}
