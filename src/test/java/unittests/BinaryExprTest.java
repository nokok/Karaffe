package unittests;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.tree.BinaryExpr;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Operator;
import org.karaffe.compiler.tree.base.Node;

public class BinaryExprTest {

    @Test
    public void testToApplyNode() {
        // ((1 + 2) + 3)
        // 1.plus(2).plus(3)
        final BinaryExpr expr = new BinaryExpr(
                new BinaryExpr(
                        new Literal.IntLiteral(new LiteralToken.IntLiteral("1")),
                        new Operator.Plus(new OperatorToken.Plus()),
                        new Literal.IntLiteral(new LiteralToken.IntLiteral("2"))),
                new Operator.Plus(new OperatorToken.Plus()),
                new Literal.IntLiteral(new LiteralToken.IntLiteral("3")));
        final Node node = expr.toApplyNode();
        Assert.assertEquals("(Apply (Select (Apply (Select (IntLiteral 1) (Plus +)) (IntLiteral 2)) (Plus +)) (IntLiteral 3))", node.toString());
    }
}
