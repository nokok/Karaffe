/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class ElementAccessTest {

    @Test
    public void testElementAccess() {
        testCode("def a = obj.field");
    }

    @Test
    public void testElementAccessOptional() {
        testCode("def a = obj field");
    }
}
