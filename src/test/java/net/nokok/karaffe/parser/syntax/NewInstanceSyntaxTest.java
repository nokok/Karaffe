/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class NewInstanceSyntaxTest {

    @Test
    public void testNewInstance() {
        testCode("def a : TypeName = TypeName");
    }

    @Test
    public void testNewInstance1() {
        testCode("def a : Int = Int()");
    }

    @Test
    public void testNewInstance2() {
        testCode("def a : Int = Int(1)");
    }
}
