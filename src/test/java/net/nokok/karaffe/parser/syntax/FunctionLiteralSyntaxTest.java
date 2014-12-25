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

public class FunctionLiteralSyntaxTest {

    @Test
    public void testSimpleFunctionLiteral() {
        testCode("foo ((_) -> _ + 1) 2\n");
    }

    @Test
    public void testFunctionLiteralWithoutParameters() {
        testCode("foo (() -> x + 1)");
    }
}
