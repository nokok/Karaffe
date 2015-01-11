/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class UnaryOpsSyntaxTest {

    @Test
    public void testSimpleUnaryOperatorTest() {
        testCode("p(-1)");
    }

    @Test
    public void testUnaryOperatorTest() {
        testCode("p(- - -1)");
    }

}
