/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FloatLiteralTest {

    @Test
    public void testPositiveFloatLiteral() {
        testCode("def a = 0.1\n");
        testCode("def a = 1.0\n");
    }

    @Test
    public void testNegativeFloatLiteral() {
        testCode("def a = -0.1\n");
        testCode("def a = -1.0\n");
    }

    @Test
    public void testFloatExp() {
        testCode("def a = 1.0e2\n");
        testCode("def a = 0.0e2\n");
        testCode("def a = 1.0E2\n");
        testCode("def a = 0.0E2\n");
    }

    @Test
    public void testFloatNegativeExp() {
        testCode("def a = 1.0e-2\n");
        testCode("def a = 0.0e-2\n");
        testCode("def a = 1.0E-2\n");
        testCode("def a = 0.0E-2\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral1() {
        testCode("def a = 0.\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral2() {
        testCode("def a = .1\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral3() {
        testCode("def a = 0.0e\n");
    }
}
