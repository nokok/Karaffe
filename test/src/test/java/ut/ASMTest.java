package ut;

import net.nokok.azm.ClassReader;
import net.nokok.azm.tree.ClassNode;
import org.junit.Test;
import org.karaffe.compiler.ast.CompilationUnit;
import org.karaffe.compiler.ast.PackageDef;
import org.karaffe.compiler.ast.names.PackageName;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.backend.jvm.ASMRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static utils.JavaCompilerUtil.compile;

public class ASMTest {

    private final ASMRunner runner = new ASMRunner();

    @Test
    public void testIntLiteralToIconst() {
        CompilationUnit compilationUnit = new CompilationUnit();
        ClassDef classDef = new ClassDef(new SimpleName(""));
        compilationUnit.addTypedefStatement(classDef);
    }

    @Test
    public void testClass1() {
        byte[] mainClassByteCode = compile("o/k/A", "package o.k; public class A {}");
        PackageDef packageDef = new PackageDef(new PackageName("o.k"));
        ClassDef classDef = new ClassDef(new SimpleName("A"));
        packageDef.addTypeDefStatement(classDef);
        this.runner.transform(packageDef);
        List<ClassNode> generatedClasses = this.runner.getClassNodes();
        assertEquals(1, generatedClasses.size());
        ClassReader classReader = new ClassReader(mainClassByteCode);
        ClassNode expectedClassNode = new ClassNode();
        classReader.accept(expectedClassNode, ClassReader.EXPAND_FRAMES);
        ClassNode classNode = generatedClasses.get(0);

        assertEquals(expectedClassNode.version, classNode.version);
        //assertEquals(Modifier.toString(expectedClassNode.access), Modifier.toString(classNode.access));
        assertEquals(expectedClassNode.name, classNode.name);
        assertEquals(expectedClassNode.signature, classNode.signature);
        assertEquals(expectedClassNode.superName, classNode.superName);
        assertEquals(expectedClassNode.interfaces, classNode.interfaces);

        assertNotNull(classNode.sourceFile);
        assertNotEquals(expectedClassNode.sourceFile, classNode.sourceFile);
        assertEquals(expectedClassNode.sourceDebug, classNode.sourceDebug);
        assertEquals(expectedClassNode.module, classNode.module);
        assertEquals(expectedClassNode.outerClass, classNode.outerClass);
        assertEquals(expectedClassNode.outerMethod, classNode.outerMethod);
        assertEquals(expectedClassNode.outerMethodDesc, classNode.outerMethodDesc);
        assertEquals(expectedClassNode.visibleAnnotations, classNode.visibleAnnotations);
        assertEquals(expectedClassNode.invisibleAnnotations, classNode.invisibleAnnotations);
        assertEquals(expectedClassNode.visibleTypeAnnotations, classNode.visibleTypeAnnotations);
        assertEquals(expectedClassNode.invisibleTypeAnnotations, classNode.invisibleTypeAnnotations);
        assertEquals(expectedClassNode.attrs, classNode.attrs);
        assertEquals(expectedClassNode.innerClasses, classNode.innerClasses);
        assertEquals(expectedClassNode.fields, classNode.fields);
        assertEquals(expectedClassNode.methods, classNode.methods);

    }
}
