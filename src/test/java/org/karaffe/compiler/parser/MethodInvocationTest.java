package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken.EOF;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.ExprParser.Primary;
import org.karaffe.compiler.parser.util.MatchResult;

public class MethodInvocationTest {
    @Test
    public void testEmpty() {
        this.runTest("", false);
    }

    @Test
    public void testMethodInvocation() {
        this.runTest("target.doSomething(expr)", true);
    }

    @Test
    public void testMethodInvocationExpr() {
        this.runTest("target.doSomething(1 + 5)", true);
    }

    @Test
    public void testMethodInvocationExpr2() {
        this.runTest("target.doSomething(1, 5)", true);
    }

    @Test
    public void testMethodInvocationExpr3() {
        this.runTest("target.doSomething(1, new int[3])", true);
    }

    @Test
    public void testMethodInvocationExpr4() {
        this.runTest("this.ComputeFac(num-1)", true);
    }

    @Test
    public void testMethodInvocationExpr5() {
        this.runTest("this.compute()", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final List<Token> input = lexer.run();
        final MatchResult result = new Primary.MethodInvocationParser().match(input);
        Assert.assertEquals(v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            if (result.next().size() >= 2) {
                Assert.fail(result.next().toString());
            }
            Assert.assertTrue(new ArrayList<>(result.next()).get(0).is(EOF.class));
        } else {
            Assert.assertEquals(String.format("%s vs %s", input, result.next()), input.size(), result.next().size());
        }
    }
}
