package ut;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.ast.CompilationUnit;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerBuilder;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerRunner;

public class ASMTest {

    private TransformerRunner runner = new TransformerRunner(new TransformerBuilder().getTransformers());
    private CompilationUnit compilationUnit = new CompilationUnit();

    @Test
    public void testClass1() {
//        byte[] mainClassByteCode = compile("o/k/A", "package o.k; public class A {}");
//        PackageDef packageDef = new PackageDef(new PackageName("o.k"));
//        ClassDef classDef = new ClassDef(new SimpleName("A"));
//        packageDef.addTypeDefStatement(classDef);
//
//
//        this.runner.transform(packageDef);
//        List<ClassNode> generatedClasses = this.runner.getClassNodes();
//        assertEquals(1, generatedClasses.size());
//        ClassReader classReader = new ClassReader(mainClassByteCode);
//        ClassNode expectedClassNode = new ClassNode();
//        classReader.accept(expectedClassNode, ClassReader.EXPAND_FRAMES);
//        ClassNode classNode = generatedClasses.get(0);
//
//        Textifier textifier = new Textifier();
//        TraceClassVisitor visitor = new TraceClassVisitor(null, new Textifier(), null);
//
//        classNode.accept(visitor);
//        assertEquals(expectedClassNode.version, classNode.version);
//        //assertEquals(Modifier.toString(expectedClassNode.access), Modifier.toString(classNode.access));
//        assertEquals(expectedClassNode.name, classNode.name);
//        assertEquals(expectedClassNode.signature, classNode.signature);
//        assertEquals(expectedClassNode.superName, classNode.superName);
//        assertEquals(expectedClassNode.interfaces, classNode.interfaces);
//
//        assertNotNull(classNode.sourceFile);
//        assertNotEquals(expectedClassNode.sourceFile, classNode.sourceFile);
//        assertEquals(expectedClassNode.sourceDebug, classNode.sourceDebug);
//        assertEquals(expectedClassNode.module, classNode.module);
//        assertEquals(expectedClassNode.outerClass, classNode.outerClass);
//        assertEquals(expectedClassNode.outerMethod, classNode.outerMethod);
//        assertEquals(expectedClassNode.outerMethodDesc, classNode.outerMethodDesc);
//        assertEquals(expectedClassNode.visibleAnnotations, classNode.visibleAnnotations);
//        assertEquals(expectedClassNode.invisibleAnnotations, classNode.invisibleAnnotations);
//        assertEquals(expectedClassNode.visibleTypeAnnotations, classNode.visibleTypeAnnotations);
//        assertEquals(expectedClassNode.invisibleTypeAnnotations, classNode.invisibleTypeAnnotations);
//        assertEquals(expectedClassNode.attrs, classNode.attrs);
//        assertEquals(expectedClassNode.innerClasses, classNode.innerClasses);
//        assertEquals(expectedClassNode.fields, classNode.fields);
//        assertEquals(expectedClassNode.methods, classNode.methods);

    }
}
