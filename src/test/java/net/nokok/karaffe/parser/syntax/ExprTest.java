/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class ExprTest {

    @Test
    public void testSimpleExpr() {
        testCode("type D{\n"
                + "func this() {\n"
                + "a = 1\n"
                + "a = 2\n"
                + "\n}"
                + "\n}");
    }

    @Test
    public void testSimpleClosedRangeOperator() {
        testExpr("1..5");
    }

    @Test
    public void testSimpleHalfOpenRangeOperator() {
        testExpr("1.<5");
    }

    @Test
    public void testSimpleClosedRangeOperator1() {
        testExpr("x.._");
    }

    @Test
    public void testInfiniteList() {
        testExpr("1.._\n");
    }

    @Test
    public void testEmptyTuple() {
        testExpr("()\n");
    }

    @Test
    public void testSingleTuple() {
        testExpr("(1)\n");
    }

    @Test
    public void testDoubleTuple() {
        testExpr("(1 2)\n");
    }

    @Test
    public void testBoolOrExpr() {
        testExpr("true | false");
    }

    @Test
    public void testBoolAndExpr() {
        testExpr("true & false");
    }

    @Test
    public void testEqualityExpr() {
        testExpr("1 == 2");
    }

    @Test
    public void testRelationalExpr() {
        testExpr("1 < 2");
    }

    @Test
    public void testRangeExpr() {
        testExpr("1..2");
    }

    @Test
    public void testAdditiveExpr() {
        testExpr("1 + 1");
    }

    @Test
    public void testMultiplicativeExpr() {
        testExpr("1 * 1");
    }

    @Test
    public void testUnaryExpr() {
        testExpr("-1");
    }

    @Test
    public void testUnaryExprNotPlusMinus() {
        testExpr("!false");
    }

    @Test
    public void testBackwardPipelineExpr() {
        testExpr("hoge |> fuga");
    }

    @Test
    public void testPipelineExpr() {
        testExpr("hoge <| fuga");
    }

    @Test
    public void testBlockArgument() {
        testExpr("someMethod {\n"
                + "1"
                + "}");
    }

    @Test
    public void testBlockArguments() {
        testExpr("someMethod {"
                + "1\n"
                + "}{\n"
                + "2\n"
                + "}{\n"
                + "3"
                + "}");
    }

    @Test
    public void testBlockArgumentChain() {
        testExpr("someMethod {\n"
                + "1\n"
                + "} someMethod2 {\n"
                + "1\n"
                + "} someMethod3 {\n"
                + "1"
                + "}\n");
    }

    @Test
    public void testAmbiguousNameBlockArgument() {
        testExpr("System.out.printf {\n"
                + "\"Hello %s\""
                + "}{\n"
                + "\"World\"\n"
                + "}\n");
    }

    @Test
    public void testMethodReference() {
        testExpr("list.forEach(Hoge::methodName)");
    }

    @Test
    public void testMethodInvocationBlockArg() {
        testExpr("list.stream().map{\n"
                + "_ * 2"
                + "}\n");
    }

    @Test
    public void testMixedMethodInvocationChain() {
        testExpr("Clazz.field.method().obj.blockArg{1}.retval.method().classField.method()");
    }

    private void testExpr(String code) {
        testCode("type D{\n"
                + "def a = "
                + code
                + "}");
    }
}
