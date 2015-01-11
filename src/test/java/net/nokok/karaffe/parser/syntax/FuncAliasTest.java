/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FuncAliasTest {

    @Test
    public void testFuncAliasWithoutArguments() {
        testCode("funcalias after() : before()");
    }

    @Test
    public void testFuncAliasWithoutArguments1() {
        testCode("funcalias after() : package.name.Class.function()");
    }

    @Test
    public void testOverloadedFunctionAlias() {
        testCode("funcalias after(x:Int y:Int) : before(x y)");
    }
}
