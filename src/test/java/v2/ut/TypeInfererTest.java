package v2.ut;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.karaffe.compiler.tree.transform.TransformerRunner;
import org.karaffe.compiler.tree.transform.typeinferer.TypeInferer;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.Parameter;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.NewInstance;
import org.karaffe.compiler.tree.v2.expressions.Plus;
import org.karaffe.compiler.tree.v2.expressions.StaticApply;
import org.karaffe.compiler.tree.v2.modifiers.Public;
import org.karaffe.compiler.tree.v2.modifiers.Static;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

import karaffe.core.Console;

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
                                                        new TypeName(new SimpleName("StringArray"))))),
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
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = 0;\n" +
                "let b = 1.+(2);\n" +
                "let c = 1.+(2).+(3);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        TypeInferer typeInferer = new TypeInferer();
        CompilationUnit after = typeInferer.transform(this.runner.transform(before, "name-resolver"));
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
                "class A extends karaffe.core.Any {\n" +
                "public static void main(args karaffe.core.StringArray) {\n" +
                "let a : karaffe.core.Int = {\n" +
                "let k_0 : karaffe.core.Int = 0;\n" +
                "return k_0;\n" +
                "};\n" +
                "let b : karaffe.core.Int = {\n" +
                "let k_1 : karaffe.core.Int = 1;\n" +
                "let k_2 : karaffe.core.Int = 2;\n" +
                "let k_3 : karaffe.core.Int = k_1.plus(k_2);\n" +
                "return k_3;\n" +
                "};\n" +
                "let c : karaffe.core.Int = {\n" +
                "let k_4 : karaffe.core.Int = 1;\n" +
                "let k_5 : karaffe.core.Int = 2;\n" +
                "let k_6 : karaffe.core.Int = k_4.plus(k_5);\n" +
                "let k_7 : karaffe.core.Int = 3;\n" +
                "let k_8 : karaffe.core.Int = k_6.plus(k_7);\n" +
                "return k_8;\n" +
                "};\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", after.toString());
    }

    @Test
    public void testCompilationUnit2() {
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
                                                        new TypeName(new SimpleName("StringArray"))))),
                                        new ArrayList<>(Arrays.asList(
                                                new LetLocalDef(
                                                        new SimpleName("a"),
                                                        new IntLiteral(0)),
                                                new LetLocalDef(
                                                        new SimpleName("b"),
                                                        new ExpressionName("a")),
                                                new LetLocalDef(
                                                        new SimpleName("c"),
                                                        new ExpressionName("b"))))))));
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = 0;\n" +
                "let b = a;\n" +
                "let c = b;\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        CompilationUnit transformed = this.runner.transform(before, "name-resolver");
        TypeInferer typeInferer = new TypeInferer();
        CompilationUnit after = typeInferer.transform(transformed);
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
                "class A extends karaffe.core.Any {\n" +
                "public static void main(args karaffe.core.StringArray) {\n" +
                "let a : karaffe.core.Int = {\n" +
                "let k_0 : karaffe.core.Int = 0;\n" +
                "return k_0;\n" +
                "};\n" +
                "let b : karaffe.core.Int = {\n" +
                "let k_1 : karaffe.core.Int = a;\n" +
                "return k_1;\n" +
                "};\n" +
                "let c : karaffe.core.Int = {\n" +
                "let k_2 : karaffe.core.Int = b;\n" +
                "return k_2;\n" +
                "};\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", after.toString());
    }

    @Test
    public void testCompilationUnit3() {
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
                                                        new TypeName(new SimpleName("StringArray"))))),
                                        new ArrayList<>(Arrays.asList(
                                                new LetLocalDef(
                                                        new SimpleName("a"),
                                                        new IntLiteral(0)),
                                                new LetLocalDef(
                                                        new SimpleName("b"),
                                                        new ExpressionName("a")),
                                                new LetLocalDef(
                                                        new SimpleName("c"),
                                                        new ExpressionName("b")),
                                                new LetLocalDef(
                                                        new SimpleName("d"),
                                                        new StaticApply(
                                                                new TypeName("Console"),
                                                                new SimpleName("println"),
                                                                new ExpressionName("c")))))))));
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = 0;\n" +
                "let b = a;\n" +
                "let c = b;\n" +
                "let d = Console.println(c);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        CompilationUnit transformed = this.runner.transform(before, "name-resolver");
        TypeInferer typeInferer = new TypeInferer();
        CompilationUnit after = typeInferer.transform(transformed);
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
                "class A extends karaffe.core.Any {\n" +
                "public static void main(args karaffe.core.StringArray) {\n" +
                "let a : karaffe.core.Int = {\n" +
                "let k_0 : karaffe.core.Int = 0;\n" +
                "return k_0;\n" +
                "};\n" +
                "let b : karaffe.core.Int = {\n" +
                "let k_1 : karaffe.core.Int = a;\n" +
                "return k_1;\n" +
                "};\n" +
                "let c : karaffe.core.Int = {\n" +
                "let k_2 : karaffe.core.Int = b;\n" +
                "return k_2;\n" +
                "};\n" +
                "let d : void = {\n" +
                "let k_3 : void = karaffe.core.Console.println(c);\n" +
                "return k_3;\n" +
                "};\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", after.toString());
    }

    @Test
    public void testNewExpr1() {
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
                                                        new TypeName(new SimpleName("StringArray"))))),
                                        new ArrayList<>(Arrays.asList(
                                                new LetLocalDef(new SimpleName("a"), new NewInstance(new TypeName(new SimpleName("Date"))))))))));
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = Date.<init>();\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        CompilationUnit transformed = this.runner.transform(before, "name-resolver");
        TypeInferer typeInferer = new TypeInferer();
        CompilationUnit after = typeInferer.transform(transformed);
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
                "class A extends karaffe.core.Any {\n" +
                "public static void main(args karaffe.core.StringArray) {\n" +
                "let a : java.util.Date = {\n" +
                "let k_0 : java.util.Date = java.util.Date.<init>();\n" +
                "return k_0;\n" +
                "};\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", after.toString());
    }

    @Test
    public void testMethodSelection() {
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
                                                        new TypeName(new SimpleName("StringArray"))))),
                                        new ArrayList<>(Arrays.asList(
                                                new LetLocalDef(new SimpleName("a"), new StaticApply(new FullyQualifiedTypeName(Console.class), new SimpleName("println")))))))));
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "class A extends Any {\n" +
                "public static void main(args StringArray) {\n" +
                "let a = karaffe.core.Console.println();\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", before.toString());
        CompilationUnit transformed = this.runner.transform(before, "name-resolver");
        TypeInferer typeInferer = new TypeInferer();
        CompilationUnit after = typeInferer.transform(transformed);
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
                "class A extends karaffe.core.Any {\n" +
                "public static void main(args karaffe.core.StringArray) {\n" +
                "let a : void = {\n" +
                "let k_0 : void = karaffe.core.Console.println();\n" +
                "return k_0;\n" +
                "};\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", after.toString());
    }

}
