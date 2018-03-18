package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.parser.ExprParser.PlusMinusParser;
import org.karaffe.compiler.parser.util.MatchResult;

public class PlusExprParserTest {

    @Test
    public void testMixedExpr() {
        final ExprParser parser = new ExprParser();
        final MatchResult result = parser.parse("1*2+3");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("(Apply (Select (Apply (Select (IntLiteral 1) (Select star)) (IntLiteral 2)) (Select plus)) (IntLiteral 3))", result.getNode().get().toString());
    }

    @Test
    public void testPlusExpr() {
        final PlusMinusParser parser = new PlusMinusParser();
        final MatchResult result = parser.parse("1+2");
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("(Apply (Select (IntLiteral 1) (Select plus)) (IntLiteral 2))", result.getNode().get().toString());
    }
}
