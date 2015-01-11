/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class ModuleDeclSyntaxTest {

    @Test
    public void testModuleDecl() {
        testCode("module Hoge\n"
                + "type Hoge");
    }

    @Test(expected = AssertionError.class)
    public void testEmptyModuleName() {
        testCode("module ");
    }

    @Test(expected = AssertionError.class)
    public void testEmptyModuleBody() {
        testCode("module invalidmodule\n"
                + "");
    }

    @Test(expected = AssertionError.class)
    public void testJavaPackageName() {
        testCode("module foo.bar.baz");
    }

}
