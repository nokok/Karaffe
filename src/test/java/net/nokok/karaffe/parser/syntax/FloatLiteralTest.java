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
        testCode("a = 0.1");
        testCode("a = 1.0");
    }

    @Test
    public void testNegativeFloatLiteral() {
        testCode("a = -0.1");
        testCode("a = -1.0");
    }

    @Test
    public void testFloatExp() {
        testCode("a = 1.0e2");
        testCode("a = 0.0e2");
        testCode("a = 1.0E2");
        testCode("a = 0.0E2");
    }

    @Test
    public void testFloatNegativeExp() {
        testCode("a = 1.0e-2");
        testCode("a = 0.0e-2");
        testCode("a = 1.0E-2");
        testCode("a = 0.0E-2");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral1() {
        testCode("a = 0.");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral2() {
        testCode("a = .1");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidFloatLiteral3() {
        testCode("a = 0.0e");
    }
}
