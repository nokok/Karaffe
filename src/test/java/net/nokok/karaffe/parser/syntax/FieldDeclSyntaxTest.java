/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FieldDeclSyntaxTest {

    @Test
    public void testSimpleFieldDecl() {
        testDecl("def a Int = undefined");
    }

    @Test
    public void testFieldDclWithoutType() {
        testDecl("def a = undefined");
    }

    @Test
    public void testVarDeclWithTypeParamType() {
        testDecl("def t T[E] = undefined");
    }

    @Test(expected = AssertionError.class)
    public void testInvalidTypeParam() {
        testDecl("def t[T] T = undefined");
    }

    @Test
    public void testVarModifier() {
        testDecl("var def t = undefined");
    }

    @Test
    public void testPublicModifier() {
        testDecl("public def t = undefined");
    }

    @Test
    public void testFieldAmbiguousType() {
        testDecl("def hoge java.lang.Integer = 0");
    }

    public static void testDecl(String code) {
        testCode("type Hoge{\n"
                + code
                + "}");
    }
}
