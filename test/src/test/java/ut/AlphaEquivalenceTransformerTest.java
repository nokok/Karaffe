package ut;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.ast.Parameter;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Block;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.IntLiteral;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Plus;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Return;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Public;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Static;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.ClassDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetLocalDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.MethodDef;
import org.karaffe.compiler.frontend.karaffe.transformer.AlphaEquivalenceTransformer;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testTransform2() {
        CompilationUnit compilationUnit = new CompilationUnit();
        assertEquals("/* Compilation Unit */ {\n" +
                "}", this.transformer.transform(compilationUnit).toString());
    }

    @Test
    public void testTransform3() {
        MethodDef mainMethod = new MethodDef(
                Arrays.asList(new Public(), new Static()),
                TypeName.voidType(),
                new SimpleName("main"),
                Arrays.asList(new Parameter(new SimpleName("args"), new TypeName(new SimpleName("StringArray")))));
        mainMethod.addMethodBody(new LetLocalDef(
                new SimpleName("a"),
                new Apply(new IntLiteral(1), new Plus(), new IntLiteral(3))));
        ClassDef classDef = new ClassDef(new SimpleName("A"));
        classDef.addMember(mainMethod);
        CompilationUnit compilationUnit = new CompilationUnit();
        compilationUnit.addTypeDefStatement(classDef);

        assertEquals("/* Compilation Unit */ {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = 1.+(3);\n" +
                "}\n" +
                "}\n" +
                "}", compilationUnit.toString());

        assertEquals("/* Compilation Unit */ {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = 1.+(3);\n" +
                "}\n" +
                "}\n" +
                "}", this.transformer.transform(compilationUnit).toString());
    }
}
