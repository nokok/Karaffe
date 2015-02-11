package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class TypeDeclSyntaxTest {

    @Test
    public void testTypeDeclSyntax() {
        testCode("type TypeName[T] < SuperType[T] <- Interface1[T] Interface2[T] {\n"
                + "}");
    }

    @Test
    public void testTypeDeclPrivateModifier() {
        testCode("private type TypeName{}");
    }

    @Test
    public void testTypeDeclPublicModifier() {
        testCode("public type TypeName{}");
    }

    @Test
    public void testTypeDeclFinalModifier() {
        testCode("final type TypeName{}");
    }
//
//    @Test
//    public void testTypeDeclSealedModifier() {
//        testCode("sealed type TypeName{}");
//    }

    @Test
    public void testTypeDeclAbstractModifier() {
        testCode("abstract type TypeName{}");
    }

    @Test
    public void testTypeParam() {
        testCode("type TypeName[T]{}");
    }

    @Test
    public void testNestedTypeParam() {
        testCode("type TypeName[Type[T]]{}");
    }

    @Test
    public void testSuperTypeTypeParam() {
        testCode("type TypeName[T] < SuperType[T]{}");
    }

    @Test(expected = AssertionError.class)
    public void testSuperTypes1() {
        testCode("type TypeName < SuperType SuperType{}");
    }

    @Test(expected = AssertionError.class)
    public void testSuperTypes2() {
        testCode("type TypeName < SuperType < SuperType{}");
    }
    
    @Test
    public void testTypeDeclAnnotation() {
        testCode("@Hoge type TypeName{}");
    }
    
    @Test
    public void testTypeDeclAnnotations() {
        testCode("@Hoge @Fuga type TypeName{}");
    }
}
