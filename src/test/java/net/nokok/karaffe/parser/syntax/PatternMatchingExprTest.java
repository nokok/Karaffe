/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class PatternMatchingExprTest {

    @Test
    public void testConstPattern() {
        testCode("def a = switch 1\n"
                + "case 1 => 1\n"
                + "case _ => 0\n");
    }

    @Test
    public void testConstPatternWithType() {
        testCode("def a = switch 1\n"
                + "case 1:Int => 1\n"
                + "case _:Int => 0\n");
    }

    @Test
    public void testTuplePattern() {
        testCode("def a = switch #(1 2)\n"
                + "case #(_ _) => true\n"
                + "case _ => false\n");
    }

    @Test
    public void testFixSeqPattern() {
        testCode("def a = switch 1..3\n"
                + "case [_ _ _] => true\n"
                + "case _ => false\n");
    }

    @Test
    public void testSomeSeqPattern() {
        testCode("def a = switch 1..3\n"
                + "case x xs => undefined\n");
    }

    @Test
    public void testCtorPattern() {
        testCode("def a = switch 1\n"
                + "case Int(1) => true\n"
                + "case _ => false\n");
    }

    @Test
    public void testVarPattern() {
        testCode("def a = switch 1\n"
                + "case x => x\n");
    }
//
//    @Test
//    public void testVarBindPattern() {
//        testCode("def a = switch #(1 2)\n"
//                + "case x : #(_ _) => undefined");
//    }

    @Test
    public void testWildcardPattern() {
        testCode("def a = switch 1\n"
                + "case _ => 1\n");
    }
}
