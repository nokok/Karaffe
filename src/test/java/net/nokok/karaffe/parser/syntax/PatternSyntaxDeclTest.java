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

public class PatternSyntaxDeclTest {

    @Test
    public void testSimplePattern() {
        testCode("def func:Int -> Int\n"
                + "func x => x * 2\n");
    }

    @Test
    public void testSimplePatternWithGuard() {
        testCode("def abs:Int -> Int\n"
                + "abs x @ x < 0 => -x;\n"
                + "abs _ => x\n");
    }

    @Test
    public void testPatternMatching_ConstPattern() {
        testCode("def isOne : Int -> Bool\n"
                + "isOne 1 => true\n"
                + "isOne _ => false\n");
    }

    @Test
    public void testPatternMatching_ConstPatternWithType() {
        testCode("def isOne : Int -> Bool\n"
                + "isOne 1:Int => true\n"
                + "isOne _:Int => false\n");
    }

}
