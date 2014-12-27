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

public class AssignmentExpressionSyntaxTest {

    @Test
    public void testExprNameAssignment() {
        testCode("hoge = 1");
    }

    @Test
    public void testInstanceMemberAssignment() {
        testCode("obj.field = 9\n");
    }
}
