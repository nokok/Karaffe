package v2.ut;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.tree.transform.AlphaEquivalenceTransformer;
import org.karaffe.compiler.tree.v2.expressions.Block;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Return;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;

public class AlphaEquivalenceTransformerTest {
    private AlphaEquivalenceTransformer transformer = new AlphaEquivalenceTransformer();

    @Test
    public void testTransform1() {
        Block block = new Block();
        block.add(new LetLocalDef(
                new SimpleName("k_0"),
                new IntLiteral(1)));
        LetLocalDef let = new LetLocalDef(
                new SimpleName("k_0"),
                new IntLiteral(2));
        block.add(new LetLocalDef(
                new SimpleName("a"),
                new Block(let, new Return(new ExpressionName("k_0")))));
        assertEquals("{\n" +
                "let k_0 = 1;\n" +
                "let a = {\n" +
                "let k_0 = 2;\n" +
                "return k_0;\n" +
                "};\n" +
                "}", block.toString());
        String transformedString = this.transformer.transform(block).toString();
        assertEquals("{\n" +
                "let k_0 = 1;\n" +
                "let a = {\n" +
                "let s_0 = 2;\n" +
                "return s_0;\n" +
                "};\n" +
                "}", transformedString);
    }
}
