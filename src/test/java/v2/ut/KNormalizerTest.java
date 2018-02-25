package v2.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.karaffe.compiler.tree.transform.KNormalizer;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.Div;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Mul;
import org.karaffe.compiler.tree.v2.expressions.Plus;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;

public class KNormalizerTest {
    private KNormalizer normalizer;

    @Before
    public void setUp() {
        normalizer = new KNormalizer();
    }

    @Test
    public void testNormalizable1() {
        IntLiteral one = new IntLiteral(1);
        assertFalse(one.isNormalizable());

        Apply apply = new Apply(one, new Plus(), new IntLiteral(2));
        assertFalse(apply.isNormalizable());
    }

    @Test
    public void testNormalizable2() {
        assertFalse(new Apply(
                new IntLiteral(1),
                new SimpleName("toString")).isNormalizable());
        assertTrue(new Apply(
                new Apply(
                        new IntLiteral(1),
                        new Plus(),
                        new IntLiteral(2)),
                new Plus(),
                new IntLiteral(3)).isNormalizable());
    }

    @Test
    public void testNomalizeTransform1() {
        Apply before = new Apply(
                new Apply(
                        new IntLiteral(1),
                        new Plus(),
                        new IntLiteral(2)),
                new Plus(),
                new IntLiteral(3));
        assertEquals("1.+(2).+(3)", before.toString());
        assertTrue(before.isNormalizable());
        Expression after = this.normalizer.transform(before);
        assertEquals("{\n" +
                "let k_0 = 1.+(2);\n" +
                "let k_1 = 3;\n" +
                "let k_2 = k_0.+(k_1);\n" +
                "}", after.toString());
        assertFalse(after.isNormalizable());
    }

    @Test
    public void testNormalizeTransform2() {
        Apply before = new Apply(
                new IntLiteral(1),
                new Plus(),
                new Apply(
                        new IntLiteral(2),
                        new Mul(),
                        new IntLiteral(3)));
        assertEquals("1.+(2.*(3))", before.toString());
        assertTrue(before.isNormalizable());
        Expression after = this.normalizer.transform(before);
        assertEquals("{\n" +
                "let k_0 = 2.*(3);\n" +
                "let k_1 = 1.+(k_0);\n" +
                "}", after.toString());
        assertFalse(after.isNormalizable());
    }

    @Test
    public void testNomalizeTransform3() {
        List<Statement> statements = new ArrayList<>();
        statements.add(new LetLocalDef(new SimpleName("a"), new Apply(
                new IntLiteral(1),
                new Plus(),
                new Apply(
                        new IntLiteral(2),
                        new Mul(),
                        new IntLiteral(3)))));
        statements.add(new LetLocalDef(new SimpleName("b"), new Apply(new IntLiteral(4), new Div(), new ExpressionName("a"))));
        Block block = new Block(statements);
        assertEquals("{\n" +
                "let a = 1.+(2.*(3));\n" +
                "let b = 4./(a);\n" +
                "}", block.toString());
        assertTrue(block.isNormalizable());
        Expression after = this.normalizer.transform(block);
        assertEquals("{\n" +
                "let a = {\n" +
                "let k_0 = 2.*(3);\n" +
                "let k_1 = 1.+(k_0);\n" +
                "k_1;\n" +
                "};\n" +
                "let b = {\n" +
                "let k_2 = 4./(a);\n" +
                "k_2;\n" +
                "};\n" +
                "}", after.toString());
        assertFalse(after.isNormalizable());
    }
}
