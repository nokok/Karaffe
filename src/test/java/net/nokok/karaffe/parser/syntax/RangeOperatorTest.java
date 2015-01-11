/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class RangeOperatorTest {

    @Test
    public void testSimpleClosedRangeOperator() {
        testCode("def a = 1..5");
    }

    @Test
    public void testSimpleHalfOpenRangeOperator() {
        testCode("def a = 1.<5");
    }

    @Test
    public void testSimpleClosedRangeOperator1() {
        testCode("def a = x.._");
    }

    @Test
    public void testInfiniteList() {
        testCode("#lazy\n"
                + "def a = 1.._\n");
    }
}
