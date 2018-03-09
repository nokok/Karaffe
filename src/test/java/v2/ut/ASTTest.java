package v2.ut;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.karaffe.compiler.tree.v2.CompilationUnit;
import org.karaffe.compiler.tree.v2.PackageDef;
import org.karaffe.compiler.tree.v2.Parameter;
import org.karaffe.compiler.tree.v2.expressions.Apply;
import org.karaffe.compiler.tree.v2.expressions.ExpressionName;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;
import org.karaffe.compiler.tree.v2.expressions.Plus;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.modifiers.Public;
import org.karaffe.compiler.tree.v2.modifiers.Static;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedTypeName;
import org.karaffe.compiler.tree.v2.names.PackageName;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.InterfaceDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

public class ASTTest {
    @Test
    public void testSimpleName() {
        assertEquals("name", new SimpleName("name").toString());
        assertEquals("foo", new SimpleName("foo").toString());
    }

    @Test
    public void testSimpleImport() {
        assertEquals("import java.lang.String;", new SimpleImport(
                new FullyQualifiedTypeName(
                        new SimpleName("java"),
                        new SimpleName("lang"),
                        new SimpleName("String"))).toString());
    }

    @Test
    public void testPackageDef1() {
        assertEquals("package <root> {\n" +
                "}", new PackageDef().toString());
    }

    @Test
    public void testPackageDef2() {
        PackageDef packageDef = new PackageDef();
        packageDef.addImportStatement(
                new SimpleImport(
                        new FullyQualifiedTypeName(
                                new SimpleName("java"),
                                new SimpleName("lang"),
                                new SimpleName("String"))));
        assertEquals("package <root> {\n" +
                "import java.lang.String;\n" +
                "}", packageDef.toString());
    }

    @Test
    public void testPackageDef3() {
        assertEquals("package karaffe.core {\n" +
                "}",
                new PackageDef(
                        new PackageName(
                                new SimpleName("karaffe"),
                                new SimpleName("core"))).toString());

    }

    @Test
    public void testClassDef() {
        ClassDef classDef = new ClassDef(new SimpleName("Derived"), new TypeName("Base"));
        assertEquals("class Derived extends Base {\n" +
                "\n" +
                "}", classDef.toString());
    }

    @Test
    public void testInterface() {
        InterfaceDef interfaceDef = new InterfaceDef(new SimpleName("I"));
        assertEquals("interface I {\n" +
                "\n" +
                "}", interfaceDef.toString());
    }

    @Test
    public void testCompilationUnit1() {
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "}\n" +
                "}", new CompilationUnit(new PackageDef()).toString());
    }

    @Test
    public void testCompilationUnit2() {
        CompilationUnit compilationUnit = new CompilationUnit();
        PackageDef packageDef = new PackageDef();
        packageDef.addImportStatement(new SimpleImport(new FullyQualifiedTypeName(new SimpleName("java"), new SimpleName("lang"), new SimpleName("Object"))));
        packageDef.addImportStatement(new SimpleImport(new FullyQualifiedTypeName(new SimpleName("java"), new SimpleName("lang"), new SimpleName("Integer"))));
        ClassDef classDef = new ClassDef(new SimpleName("A"), new TypeName("Object"));
        MethodDef mainMethod = new MethodDef(
                Arrays.asList(new Public(), new Static()),
                TypeName.voidType(),
                new SimpleName("main"),
                Arrays.asList(new Parameter(
                        new SimpleName("args"),
                        new TypeName(
                                new SimpleName("Array"),
                                Arrays.asList(new TypeName(new SimpleName("String")))))));
        mainMethod.addMethodBody(new LetLocalDef(new SimpleName("a"), new IntLiteral(1)));
        mainMethod.addMethodBody(new LetLocalDef(new SimpleName("b"), new IntLiteral(2)));
        mainMethod.addMethodBody(new Apply(new ExpressionName("a"), new Plus(), new ExpressionName("b")));
        classDef.addMember(mainMethod);
        packageDef.addTypeDefStatement(classDef);
        compilationUnit.addPackageDef(packageDef);
        assertEquals("/* Compilation Unit */ {\n" +
                "package <root> {\n" +
                "import java.lang.Object;\n" +
                "import java.lang.Integer;\n" +
                "class A extends Object {\n" +
                "public static void main(args Array[String]) {\n" +
                "let a = 1;\n" +
                "let b = 2;\n" +
                "a.+(b);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}", compilationUnit.toString());
    }
}
