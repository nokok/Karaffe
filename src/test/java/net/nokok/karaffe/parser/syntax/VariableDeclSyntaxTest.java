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

public class VariableDeclSyntaxTest {

    @Test
    public void testVariableDeclarationExplicitType() {
        testCode("def a:Int=undefined\n");
    }

    @Test
    public void testVarDcl1() {
        testCode("def x : Int = undefined\n");
    }

    @Test
    public void testVarDecl2() {
        testCode("def x : T[E] = undefined\n");
    }

    @Test
    public void variableNoTypeDeclaration() {
        testCode("def a = 2\n");
    }

    @Test
    public void testTupleDecl() {
        testCode("def t : Tuple[Int Int] = #(1 2)\n");
    }

    @Test
    public void testWildCardId() {
        testCode("def _ = undefined");
    }
}
