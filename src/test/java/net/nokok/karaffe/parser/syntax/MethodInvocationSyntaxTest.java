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

public class MethodInvocationSyntaxTest {

    @Test
    public void testSimpleMethodInvocation() {
        testCode("println(\"hoge\")");
    }

    @Test
    public void testSimpleMethodInvocation1() {
        testCode("p()");
    }

    @Test
    public void testNestedMethodInvocation() {
        testCode("a(b(c()))");
    }

    @Test
    public void testJavaStyleMethodChain() {
        testCode("foo().bar().baz()");
    }

    @Test
    public void testJavaStyleMethodChain1() {
        testCode("package.name.method().chain()");
    }

    @Test
    public void testMethodChain() {

    }

    @Test
    public void testInstanceMethodInvocation() {
        testCode("hoge.foo()");
    }

    @Test
    public void testMethodArg() {
        testCode("hoge(1 + 2)");
    }

    @Test
    public void testMethodArg1() {
        testCode("hoge(\"hoge\" + hoge.fuga)");
    }

    @Test
    public void testMethodArg2() {
        testCode("hoge(a.b())");
    }

    @Test
    public void testMethodInvocationOptionalElementAccess() {
        testCode("obj field method()");
    }

    @Test
    public void testMethodInvocationPOptionalElementAccess1() {
        testCode("obj.field method()");
    }

    @Test
    public void testMethodInvocationPOptionalElementAccess2() {
        testCode("obj field.method()");
    }

    @Test
    public void testMethodInvocationElementAccess() {
        testCode("obj.field.method()");
    }

    @Test
    public void testLongNameMethodInvocation() {
        testCode("java.lang.System.out.println()");
    }

    @Test
    public void testPrimaryMethodInvocation() {
        testCode("\"HelloWorld\".toUpperCase()");
    }

    @Test
    public void testPrimaryMethodInvocation1() {
        testCode("(1).toString()");
    }

    @Test
    public void testMethodInvocationFromExpr() {
        testCode("(1..5).forEach(() -> println(_))");
    }
}
