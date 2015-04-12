/**
 * Karaffe Programming Language
 */
package karaffe.compiler.phase.parser;

import static karaffe.compiler.phase.parser.TestUtil.testCode;
import org.junit.Test;

public class ClassDeclTest {

    @Test
    public void testSimpleClassDecl() {
        testCode("class Identifier {\n"
                + "}");
    }

    @Test
    public void testSimpleClassDeclPublicModifier() {
        testCode("public class Id {}");
    }

    @Test
    public void testSimpleClassDeclPrivateModifier() {
        testCode("private class Id {}");
    }

    @Test
    public void testSimpleClassDeclAbstractModifier() {
        testCode("abstract class Id {}");
    }

    @Test
    public void testSimpleClassDeclFinalModifier() {
        testCode("final class Id {}");
    }

    @Test
    public void testSimpleClassDeclPublicAbstractModifier() {
        testCode("public abstract class Id {}");
    }

    @Test
    public void testSimpleClassDeclWithFieldDecl() {
        testCode("class Id {\n"
                + "def id = hoge\n"
                + "\n}");
    }

    @Test
    public void testFieldDeclPrivate() {
        testCode("class Id {\n"
                + "private def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testFieldDeclPublic() {
        testCode("class Id {\n"
                + "public def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testFieldDeclFinal() {
        testCode("class Id {\n"
                + "final def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testFieldDeclStatic() {
        testCode("class Id {\n"
                + "static def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testFieldDeclNullable() {
        testCode("class Id {\n"
                + "nullable def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testFieldDeclPublicStaticFinal() {
        testCode("class Id {\n"
                + "public static final def hoge = hoge"
                + "}\n");
    }

    @Test
    public void testEmptyAutoDecl() {
        testCode("class User(){}");
    }

    @Test
    public void testAutoDecl() {
        testCode("class User(name String, age Int) {"
                + ""
                + "}");
    }

}
