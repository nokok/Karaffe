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
        testCode("p #()");
    }

    @Test
    public void testSingleTuple() {
        testCode("p #(hoge)");
    }

    @Test
    public void testDoubleTuple() {
        testCode("p #(hoge hoge)");
    }
}
