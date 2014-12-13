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

public class IntLiteralTest {

    @Test
    public void testPositiveValue() {
        testCode("a = 1");
    }

    @Test
    public void testNegativeValue() {
        testCode("a = -1");
    }

    @Test
    public void testHex() {
        testCode("a = 0xff");
        testCode("a = 0XFF");
    }

    @Test
    public void testNegativeHex() {
        testCode("a = -0xff");
        testCode("a = -0XFF");
    }

    @Test(expected = AssertionError.class)
    public void testStartWithZero() {
        testCode("a = 0123");
    }
}
