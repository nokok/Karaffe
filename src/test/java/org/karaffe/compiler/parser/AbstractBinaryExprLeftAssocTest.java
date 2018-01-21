package org.karaffe.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.CommonToken;
import org.karaffe.compiler.lexer.LiteralToken;
import org.karaffe.compiler.lexer.OperatorToken;
import org.karaffe.compiler.lexer.OperatorToken.Plus;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.ExprParser.IntLiteralParser;
import org.karaffe.compiler.parser.util.AbstractBinaryExprLeftAssoc;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.TokenMatcher;
import org.karaffe.compiler.tree.base.Node;

public class AbstractBinaryExprLeftAssocTest {

    class PlusExpr extends AbstractBinaryExprLeftAssoc {

        public PlusExpr() {
            super(new IntLiteralParser(), TokenMatcher.create(Plus.class));
        }

    }

    @Test
    public void testLeftRecursive() {
        final List<Token> input = new ArrayList<>();
        input.add(new LiteralToken.IntLiteral("1"));
        input.add(new OperatorToken.Plus());
        input.add(new LiteralToken.IntLiteral("1"));
        input.add(new OperatorToken.Plus());
        input.add(new LiteralToken.IntLiteral("1"));
        input.add(new CommonToken.Semi());
        final PlusExpr plusExpr = new PlusExpr();
        final MatchResult result = plusExpr.parse(input);
        Assert.assertTrue(result.isSuccess());
        final Optional<Node> node = result.getNode();
        Assert.assertTrue(node.isPresent());
        final Node n = node.get();
        Assert.assertEquals("(Apply (Select (Apply (Apply (Select (IntLiteral 1) ()) (IntLiteral 1))) ()) (IntLiteral 1))", n.toString());

    }
}
