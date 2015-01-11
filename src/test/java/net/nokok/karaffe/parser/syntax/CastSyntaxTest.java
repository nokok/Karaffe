/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class CastSyntaxTest {

    @Test
    public void testSimpleCastSyntax() {
        testCode("def a : Double = (1)~>Double");
    }

    @Test
    public void testNestedCast() {
        testCode("def a : Int = ((1)~>Double)~>Int");
    }
}
