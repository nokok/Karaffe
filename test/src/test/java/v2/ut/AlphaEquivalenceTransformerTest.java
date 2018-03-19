package v2.ut;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.backend.transformer.namer.AlphaEquivalenceTransformer;
import org.karaffe.compiler.ast.expressions.Block;
import org.karaffe.compiler.ast.expressions.ExpressionName;
import org.karaffe.compiler.ast.expressions.IntLiteral;
import org.karaffe.compiler.ast.expressions.Return;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.statements.LetLocalDef;

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
