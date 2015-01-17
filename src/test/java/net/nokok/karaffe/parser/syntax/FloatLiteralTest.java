/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FloatLiteralTest {

    @Test
    public void testPositiveFloatLiteral() {
        testExpression("0.1\n");
        testExpression("1.0\n");
    }

    @Test
    public void testNegativeFloatLiteral() {
        testExpression("-0.1\n");
        testExpression("-1.0\n");
    }

    @Test
    public void testFloatExp() {
        testExpression("1.0e2\n");
        testExpression("0.0e2\n");
        testExpression("1.0E2\n");
        testExpression("0.0E2\n");
    }

    @Test
    public void testFloatNegativeExp() {
        testExpression("1.0e-2\n");
        testExpression("0.0e-2\n");
        testExpression("1.0E-2\n");
        testExpression("0.0E-2\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral1() {
        testExpression("0.\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral2() {
        testExpression(".1\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral3() {
        testExpression("0.0e\n");
    }

    private void testExpression(String code) {
        testCode("type D{\n"
                + "def a = "
                + code
                + "}");
    }
}
