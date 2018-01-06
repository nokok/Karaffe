package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.util.KNormalizer;

public class KNormalizeTest {
    @Test
    public void testBooleanValue1() {
        Assign node = new Assign(new Name(new IdentifierToken.VarName("t")), new Literal(new KeywordToken.True()));

        // Before
        assertEquals("(Assign (Name t) (Literal true))", node.toString());
        KNormalizer kNormalizer = new KNormalizer();
        kNormalizer.normalize(node);

        // After
        assertEquals("(Assign (Name t) (Literal 1))", node.toString());
    }

    @Test
    public void testBooleanValue2() {
        Assign node = new Assign(new Name(new IdentifierToken.VarName("t")), new Literal(new KeywordToken.False()));

        // Before
        assertEquals("(Assign (Name t) (Literal false))", node.toString());
        KNormalizer kNormalizer = new KNormalizer();
        kNormalizer.normalize(node);

        // After
        assertEquals("(Assign (Name t) (Literal 0))", node.toString());
    }

    @Test
    public void testNormalize2() {
        Parser parser = new ExprParser();
        MatchResult result = parser.parse(new KaraffeLexer("1 * 2 + 3").run());
        Node node = result.getNode().get();

        // Before
        assertEquals("(Apply (Select (Apply (Select (Literal 1) (Select (Name *))) (Literal 2)) (Select (Name +))) (Literal 3))", node.toString());
        KNormalizer kNormalizer = new KNormalizer();
        kNormalizer.normalize(node);

        // After
        assertEquals("(ValDef (Modifiers ()) (Name e1) (TypeName int) (Apply (Select (Literal 1) (Select (Name *))) (Literal 2))) "
                + "(ValDef (Modifiers ()) (Name e2) (TypeName int) (Apply (Select (Select (Name e1)) (Select (Name +))) (Literal 3)))", node.toString());
    }

}
