package ut;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.ast.ModuleDef;
import org.karaffe.compiler.frontend.karaffe.ast.PackageDef;
import org.karaffe.compiler.frontend.karaffe.ast.Parameter;
import org.karaffe.compiler.frontend.karaffe.ast.api.ModuleDirective;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.IntLiteral;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Plus;
import org.karaffe.compiler.frontend.karaffe.ast.imports.SimpleImport;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Public;
import org.karaffe.compiler.frontend.karaffe.ast.modifiers.Static;
import org.karaffe.compiler.frontend.karaffe.ast.modules.ExportsDef;
import org.karaffe.compiler.frontend.karaffe.ast.modules.RequiresDef;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.ModuleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;
import org.karaffe.compiler.frontend.karaffe.ast.statements.ClassDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.InterfaceDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetLocalDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.MethodDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

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
    public void testModuleDef1() {
        ModuleDef moduleDef = ModuleDef.rootModule();
        assertEquals("module <root> {\n" +
                "}", moduleDef.toString());
    }

    @Test
    public void testModuleDef2() {
        ModuleDef moduleDef = new ModuleDef(new ModuleName("org", "karaffe"));
        assertEquals("module org.karaffe {\n" +
                "}", moduleDef.toString());
    }

    @Test
    public void testModuleDef3() {
        ModuleDef moduleDef = new ModuleDef("org.karaffe");
        moduleDef.setOpenModule();
        assertEquals("open module org.karaffe {\n" +
                "}", moduleDef.toString());
    }

    @Test
    public void testModuleDirective() {
        Map<String, ModuleDirective> tests = new HashMap<>();
        tests.put("requires org.some", new RequiresDef(new ModuleName("org", "some")));
        tests.put("requires static org.some", new RequiresDef(new ModuleName("org", "some"), false, true));
        tests.put("requires transitive org.some", new RequiresDef(new ModuleName("org", "some"), true, false));
        tests.put("exports org.karaffe.api to org.karaffe", new ExportsDef(new PackageName("org.karaffe.api"), new ArrayList<>(Collections.singleton(new ModuleName("org.karaffe")))));
        tests.put("exports org.karaffe.api", new ExportsDef(new PackageName("org.karaffe.api")));

        for (Map.Entry<String, ModuleDirective> entry : tests.entrySet()) {
            assertEquals(entry.getKey(), entry.getValue().toString());
        }
    }

    @Test
    public void testCompilationUnit1() {
        assertEquals("/* Compilation Unit */ {\n" +
                "module <root> {\n" +
                "}\n" +
                "}", new CompilationUnit().withModuleDef(ModuleDef.rootModule()).toString());
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
        compilationUnit.withPackageDef(ModuleDef.rootModuleName(), packageDef);
        assertEquals("/* Compilation Unit */ {\n" +
                "module <root> {\n" +
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
                "}\n" +
                "}", compilationUnit.toString());
    }

}
