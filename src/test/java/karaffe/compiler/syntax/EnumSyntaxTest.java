/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class EnumSyntaxTest {

    @Test
    public void testEnumSyntax() {
        testCode("enum EnumType [Hoge,Fuga,Piyo]\n");
    }

    @Test(expected = AssertionError.class)
    public void testEmptyArray() {
        testCode("enum EnumType []\n");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidEnumDecl() {
        testCode("enum Hoge\n");
    }

}
