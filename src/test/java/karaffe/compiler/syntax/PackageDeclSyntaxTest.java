/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class PackageDeclSyntaxTest {

    @Test
    public void testPackageDecl() {
        testCode("package Hoge");
    }

    @Test
    public void testEmptyPackageName() {
        testCode("package ");
        //TODO
    }

    @Test
    public void testJavaPackageName() {
        testCode("package foo.bar.baz");
    }

}
