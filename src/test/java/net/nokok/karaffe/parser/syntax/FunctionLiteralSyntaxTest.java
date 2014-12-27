/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FunctionLiteralSyntaxTest {

    @Test
    public void testSimpleFunctionLiteral() {
        testCode("(a) -> (a + 1)(2)\n");
    }

    @Test
    public void testFunctionLiteralWithoutParameters() {
        testCode("() -> {x + 1}()");
    }

    @Test
    public void testFuncLiteral1() {
        testCode("(x) -> {x + y()} ()");
    }

    @Test
    public void testFuncLiteral2() {
        testCode("(x) -> {x + 1}()");
    }

    @Test
    public void testFuncLiteral3() {
        testCode("() -> {#()}()");
    }
}
