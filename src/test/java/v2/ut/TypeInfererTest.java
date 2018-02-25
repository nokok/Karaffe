package v2.ut;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.karaffe.compiler.tree.transform.TransformerRunner;
import org.karaffe.compiler.tree.transform.TypeInferer;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.Parameter;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Plus;
import org.karaffe.compiler.tree.v2.modifiers.Public;
import org.karaffe.compiler.tree.v2.modifiers.Static;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

public class TypeInfererTest {

    private TransformerRunner runner;

    @Before
    public void setUp() {
        this.runner = new TransformerRunner();
    }

    @Test
    public void testCompilationUnit1() {
        CompilationUnit before = new CompilationUnit(
                new PackageDef(
                        new ClassDef(
                                new SimpleName("A"),
                                new MethodDef(
                                        new ArrayList<>(Arrays.asList(new Public(), new Static())),
                                        TypeName.voidType(),
                                        new SimpleName("main"),
                                        new ArrayList<>(Arrays.asList(
                                                new Parameter(
                                                        new SimpleName("args"),
                                                        new TypeName(new SimpleName("Array"), new TypeName("String"))))),
                                        new ArrayList<>(Arrays.asList(
                                                new LetLocalDef(
                                                        new SimpleName("a"),
                                                        new IntLiteral(0)),
                                                new LetLocalDef(
                                                        new SimpleName("b"),
                                                        new Apply(new IntLiteral(1), new Plus(), new IntLiteral(2))),
                                                new LetLocalDef(
                                                        new SimpleName("c"),
                                                        new Apply(
                                                                new Apply(
                                                                        new IntLiteral(1),
                                                                        new Plus(),
                                                                        new IntLiteral(2)),
                                                                new Plus(),
                                                                new IntLiteral(3)))))))));
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "import java.lang._;\n" +
                "import java.io._;\n" +
                "import java.net._;\n" +
                "import java.util._;\n" +
                "import java.time._;\n" +
                "import java.time.chrono._;\n" +
                "import java.time.LocalDateTime;\n" +
                "import java.time.chrono.JapaneseEra;\n" +
                "import karaffe.core._;\n" +
                "class A extends Any {\n" +
                "public static void main(args Array[String]) {\n" +
                "let a = 0;\n" +
                "let b = 1.+(2);\n" +
                "let c = 1.+(2).+(3);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        CompilationUnit after = this.runner.transform(before, TypeInferer.name);
        assertEquals("", after.toString());
    }
}
