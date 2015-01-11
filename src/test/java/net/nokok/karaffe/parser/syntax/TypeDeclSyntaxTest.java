package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class TypeDeclSyntaxTest {

    @Test
    public void testSimpleTypeDeclaration() {
        testCode("type Identifier\n");
    }

    @Test
    public void testExtendsType() {
        testCode("type Type : Super\n");
    }

    @Test
    public void testImplementsInterface() {
        testCode("type Type <- Interface1\n");
    }

    @Test
    public void testImplementsInterfaces() {
        testCode("type Type <- IF1 IF2\n");
    }

    @Test
    public void testTypeDeclarationWithTypeParameter() {
        testCode("type Identifier[A] : Super[B] <- Interface[C]\n");
    }

    @Test
    public void testTypeDeclNestedTypeParameter() {
        testCode("type Identifier[Type[A]]\n");
    }

    @Test
    public void testTypeDeclNestedTypeParameters() {
        testCode("type Identifier[Type[Type[B]]]\n");
    }

    @Test
    public void testTypeDeclBody_VarDecl() {
        testCode("type D\n"
                + "def a : Int = 0");
    }

    @Test
    public void testTypeDeclBody_VarDeclAndMethodInvocation() {
        testCode("type D\n"
                + "def a : Int = 0\n"
                + "a.toString()");
    }

}
