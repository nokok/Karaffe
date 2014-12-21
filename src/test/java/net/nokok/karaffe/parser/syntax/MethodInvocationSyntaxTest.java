/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
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
    public void testNestedMethodInvocation() {
        testCode("a(b(c()))");
    }

    @Test
    public void testJavaStyleMethodChain() {
        testCode("foo().bar().baz()");
    }

    @Test
    public void testMethodChain() {
        testCode("foo() bar() baz()");
    }

    @Test
    public void testMethodArg() {
        testCode("hoge (1 + 2)");
    }

}
