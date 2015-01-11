/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
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
        testCode("def a = 1\n");
    }

    @Test
    public void testNegativeValue() {
        testCode("def a = -1\n");
    }

    @Test
    public void testHex() {
        testCode("def a = 0xff\n");
        testCode("def a = 0XFF\n");
    }

    @Test
    public void testNegativeHex() {
        testCode("def a = -0xff\n");
        testCode("def a = -0XFF\n");
    }

    @Test(expected = AssertionError.class)
    public void testStartWithZero() {
        testCode("def a = 0123\n");
    }
}
