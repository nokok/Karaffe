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

public class VariableDeclSyntaxTest {
//VariableDeclaration

    @Test
    public void testVariableDeclarationExplicitType() {
        testCode("a:Int=undefined");
        testCode("b:Int=undefined\n");
        testCode("c : Int = undefined\n");
        testCode("d : Int = undefined\n");
        testCode("e : Int\n");
        testCode("f : Int\n");
        testCode("g = 2 //hogehoge\n");
        testCode("h = 2 /* */\n");
    }

    @Test
    public void testVarDcl1() {
        testCode("x : Int");
    }

    @Test
    public void testVariableDeclarationModifier_Private() {
        testCode("private x : Int");
        testCode("private var x : Int");
        testCode("var private x : Int");
    }

    @Test
    public void testVariableDeclarationModifier_Variable() {
        testCode("var x:Int\n");
        testCode("private var x: Int\n");
    }

    @Test(expected = AssertionError.class)
    public void testReservedKeywordIdentifier() {
        testCode("var var:Int");
    }

    @Test
    public void variableNoTypeDeclaration() {
        testCode("a = 2\n");
    }
}
