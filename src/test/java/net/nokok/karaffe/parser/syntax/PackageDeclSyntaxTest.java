/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
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
