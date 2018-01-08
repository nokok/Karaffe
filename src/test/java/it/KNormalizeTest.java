package it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.KeywordToken;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.phases.knormalize.KNormalizer;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Literal;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.base.Node;

public class KNormalizeTest {
    @Test
    public void testBooleanValue1() {
        Assign before = new Assign(new Name(new IdentifierToken.VarName("t")), new Literal(new KeywordToken.True()));

        KNormalizer kNormalizer = new KNormalizer();
        Node after = kNormalizer.normalize(before);
        assertEquals("(Assign (Name t) (Literal true))", before.toString());
        assertEquals("(Assign (Name t) (Literal 1))", after.toString());
    }

    @Test
    public void testBooleanValue2() {
        Assign before = new Assign(new Name(new IdentifierToken.VarName("t")), new Literal(new KeywordToken.False()));

        KNormalizer kNormalizer = new KNormalizer();
        Node after = kNormalizer.normalize(before);
        assertEquals("(Assign (Name t) (Literal false))", before.toString());
        assertEquals("(Assign (Name t) (Literal 0))", after.toString());
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
