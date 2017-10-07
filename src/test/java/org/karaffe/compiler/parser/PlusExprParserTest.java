package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.ExprParser.PlusMinusParser;
import org.karaffe.compiler.parser.util.MatchResult;

public class PlusExprParserTest {

    @Test
    public void testPlusExpr() {
        final PlusMinusParser parser = new PlusMinusParser();
        final MatchResult result = parser.match(new KaraffeLexer("1+2").run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("(Apply (Select (Literal 1) (Select (Name +))) (Literal 2))", result.getNode().get().toString());
    }

    @Test
    public void testMixedExpr() {
        final ExprParser parser = new ExprParser();
        final MatchResult result = parser.match(new KaraffeLexer("1*2+3").run());
        Assert.assertEquals(true, result.isSuccess());
        Assert.assertEquals("(Apply (Select (Apply (Select (Literal 1) (Select (Name *))) (Literal 2)) (Select (Name +))) (Literal 3))", result.getNode().get().toString());
    }
}
