package ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.StaticApply;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.transformer.KNormalizer;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Block;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Div;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.IntLiteral;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Mul;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Plus;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetLocalDef;

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
        assertTrue(apply.isNormalizable());
    }

    @Test
    public void testNormalizable2() {
        assertTrue(new Apply(
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
                "let k_0 = 1;\n" +
                "let k_1 = 2;\n" +
                "let k_2 = k_0.+(k_1);\n" +
                "let k_3 = 3;\n" +
                "let k_4 = k_2.+(k_3);\n" +
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
                "let k_0 = 1;\n" +
                "let k_1 = 2;\n" +
                "let k_2 = 3;\n" +
                "let k_3 = k_1.*(k_2);\n" +
                "let k_4 = k_0.+(k_3);\n" +
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
                "let k_0 = 1;\n" +
                "let k_1 = 2;\n" +
                "let k_2 = 3;\n" +
                "let k_3 = k_1.*(k_2);\n" +
                "let k_4 = k_0.+(k_3);\n" +
                "return k_4;\n" +
                "};\n" +
                "let b = {\n" +
                "let k_5 = 4;\n" +
                "let k_6 = a;\n" +
                "let k_7 = k_5./(k_6);\n" +
                "return k_7;\n" +
                "};\n" +
                "}", after.toString());
        assertFalse(after.isNormalizable());
    }

    @Test
    public void testNOrmalizeTranform4() {
        StaticApply staticApply = new StaticApply(
                new TypeName("Foo"),
                new SimpleName("doSomething"),
                Collections.singletonList(new IntLiteral(1)));
        assertEquals("Foo::doSomething(1)", staticApply.toString());

        Expression expr = this.normalizer.transform(staticApply);
        assertEquals("{\n" +
                "let k_0 = 1;\n" +
                "Foo::doSomething(k_0);\n" +
                "}", expr.toString());
    }

}
