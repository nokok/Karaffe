/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class AssignmentExpressionSyntaxTest {

    @Test
    public void testExprNameAssignment() {
        testAssignment("a = 1");
    }

    @Test
    public void testInstanceMemberAssignment() {
        testAssignment("obj.field = 9");
    }

    @Test
    public void testMethodValueAssignment() {
        testAssignment("x = hoge()");
    }

    @Test
    public void testElementAccessAssignment() {
        testAssignment("a = c.toArray()\n");
    }

    private void testAssignment(String code) {
        testCode("type D{\n"
                + "func this() {\n"
                + code
                + "\n}"
                + "\n}");
    }
}
