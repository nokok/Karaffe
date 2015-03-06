/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FunctionLiteralSyntaxTest {

    @Test
    public void testSimpleFunctionLiteral() {
        testFunctionLiteral("(a Int) -> {a + 1}\n");
    }

    @Test
    public void testFunctionLiteralWithoutParameters() {
        testFunctionLiteral("() -> {x + 1}");
    }

    @Test
    public void testFuncLiteral1() {
        testFunctionLiteral("(x Int) -> {x + y()}");
    }

    @Test
    public void testFuncLiteral2() {
        testFunctionLiteral("x -> {x + 1}");
    }

    @Test
    public void testFuncLiteral3() {
        testFunctionLiteral("() -> {t[]}");
    }

    @Test
    public void testFuncLiteral4() {
        testFunctionLiteral("x -> {x}(2)");
    }

    @Test
    public void testFuncLiteralParameters() {
        testFunctionLiteral("(x Int) -> {x}(2)");
    }

    @Test
    public void testFuncLiteralParameters1() {
        testFunctionLiteral("(x Int y Int) -> {x + y}(2)");
    }

    @Test
    public void testFuncLiteralMethodInvocation() {
        testFunctionLiteral("(x) -> {1}.toString()");
    }

    @Test
    public void testFuncLiteralMethodInvocation1() {
        testFunctionLiteral("((x) -> {1}).toString()");
    }

    private void testFunctionLiteral(String code) {
        testCode("type D{\n"
                + "def a = " + code
                + "}");
    }
}
