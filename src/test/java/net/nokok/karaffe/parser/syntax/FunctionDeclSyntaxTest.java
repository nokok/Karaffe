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

public class FunctionDeclSyntaxTest {

    @Test
    public void testSimpleFunctionDeclSyntax() {
        testCode("func : Int -> Int = undefined");
    }

    @Test
    public void testSimpleCurriedFunctionDeclSyntax() {
        testCode("func : Int -> Int -> Int = undefined");
    }

    @Test
    public void testUncurriedFunctionDeclSyntax() {
        testCode("func : Int Int -> Int = undefined");
    }

    @Test
    public void testMixedFunctionDeclSyntax() {
        testCode("func : Int -> Int String -> Int -> Int = undefined");
    }

    @Test
    public void testInvalidReturnType() {
        testCode("func : Int Int = undefined");
    }

}
