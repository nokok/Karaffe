/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class MethodInvocationSyntaxTest {

    @Test
    public void testSimpleMethodInvocation() {
        testExpr("println(\"hoge\")");
    }

    @Test
    public void testSimpleMethodInvocation1() {
        testExpr("p()");
    }

    @Test
    public void testNestedMethodInvocation() {
        testExpr("a(b(c()))");
    }

    @Test
    public void testJavaStyleMethodChain() {
        testExpr("foo().bar().baz()");
    }

    @Test
    public void testJavaStyleMethodChain1() {
        testExpr("pa.name.method().chain()");
    }

    @Test
    public void testInstanceMethodInvocation() {
        testExpr("hoge.foo()");
    }

    @Test
    public void testMethodArg() {
        testExpr("hoge(1 + 2)");
    }

    @Test
    public void testMethodArg1() {
        testExpr("hoge(\"hoge\" + hoge.fuga)");
    }

    @Test
    public void testMethodArg2() {
        testExpr("hoge(a.b())");
    }

    @Test
    public void testMethodInvocationElementAccess() {
        testExpr("obj.field.method()");
    }

    @Test
    public void testLongNameMethodInvocation() {
        testExpr("java.lang.System.out.println()");
    }

    @Test
    public void testPrimaryMethodInvocation() {
        testExpr("\"HelloWorld\".toUpperCase()");
    }

    @Test
    public void testPrimaryMethodInvocation1() {
        testExpr("(1).toString()");
    }

    @Test
    public void testMethodInvocationFromExpr() {
        testExpr("(1..5).forEach(() -> {println(_)})");
    }

    @Test
    public void testNewInstanceMethodInvocation() {
        testExpr("TypeName.hoge()");
    }

    @Test
    public void testMultiLineMethodInvocation() {
        testCode("type HelloWorld{\n"
                + "func main(args List[String]) {\n"
                + "Base().say()\n"
                + "Derived().say()\n"
                + "}\n"
                + "}\n");
    }

    private void testExpr(String code) {
        testCode("type D{\n"
                + "func this(){\n"
                + code + "\n"
                + "}\n"
                + "}");
    }
}
