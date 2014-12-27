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

public class TupleSyntaxTest {

    @Test
    public void testEmptyTuple() {
        testCode("def a : Tuple0 = #()\n");
    }

    @Test
    public void testSingleTuple() {
        testCode("def a : Tuple1[Int] = #(1)\n");
    }

    @Test
    public void testDoubleTuple() {
        testCode("def a : Tuple2[Int Int] = #(1 2)\n");
    }
}
