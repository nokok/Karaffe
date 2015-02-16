/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FuncAliasTest {

    @Test
    public void testFuncAliasWithoutArguments() {
        testCode("type T{"
                + "func this(){\n"
                + "funcalias before() -> after()\n"
                + "}\n"
                + "}");
    }

    @Test
    public void testFuncAliasWithoutArguments1() {
        testCode("type T{\n"
                + "func this() {\n"
                + "funcalias pa.name.Class.function() -> after()\n"
                + "}\n"
                + "}");
    }

    @Test
    public void testOverloadedFunctionAlias() {
        testCode("type T{\n"
                + "func this() {\n"
                + "funcalias before(x Int,y Int) -> after(x Int,y Int) \n"
                + "}\n"
                + "}");
    }

    @Test
    public void testPrivateFuncAlias() {
        testCode("type Hoge{\n"
                + "funcalias hoge() -> fuga()\n"
                + "}");
    }

    @Test
    public void testInternalFuncAlias() {
        testCode("funcalias hoge() -> fuga()");
    }
}
