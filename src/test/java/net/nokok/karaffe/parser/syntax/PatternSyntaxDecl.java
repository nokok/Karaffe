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

public class PatternSyntaxDecl {

    @Test
    public void testSimplePattern() {
        testCode("def func:(Int -> Int)\n"
                + "func x = x * 2");
    }

    @Test
    public void testSimplePatternWithGuard() {
        testCode("def abs:(Int -> Int)\n"
                + "abs x @ x > 0 = x\n"
                + "abs x = -x\n");
    }
}
