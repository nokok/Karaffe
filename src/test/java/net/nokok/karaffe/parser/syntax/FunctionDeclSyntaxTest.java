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
        testCode("def func : (Int -> Int) = undefined\n");
    }

    @Test
    public void testFunction2Decl() {
        testCode("def func : (Int Int -> Int) = undefined\n");
    }

    @Test
    public void testFunction3Decl() {
        testCode("def func : (Int Int Int -> Int) = undefined\n");
    }

    @Test
    public void testFunctionTypeDecl() {
        testCode("def func : (Int -> Int)\n");
    }

    @Test
    public void testReturnFunctionType() {
        testCode("def func : (Int -> ((String -> Int) -> Int)) = undefined\n");
    }

    @Test
    public void testVoidReturnType() {
        testCode("def fund : (() -> ()) = undefined\n");
    }

}
