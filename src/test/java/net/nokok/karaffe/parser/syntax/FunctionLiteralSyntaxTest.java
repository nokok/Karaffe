/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
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

    @Test
    public void testFuncLiteral4() {
        testCode("x -> {x}(2)");
    }

    @Test
    public void testFuncLiteralParameters() {
        testCode("(x:Int) -> {x}(2)");
    }

    @Test
    public void testFuncLiteralParameters1() {
        testCode("(x:Int y:Int) -> {x + y}(2)");
    }

    @Test
    public void testFuncLiteralMethodInvocation() {
        testCode("((x) -> 1).toString()");
    }

}
