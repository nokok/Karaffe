/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class PackageDeclSyntaxTest {

    @Test
    public void testModuleDecl() {
        testCode("package Hoge");
    }

    @Test(expected = AssertionError.class)
    public void testEmptyModuleName() {
        testCode("package ");
    }

    public void testJavaPackageName() {
        testCode("package foo.bar.baz");
    }

}
