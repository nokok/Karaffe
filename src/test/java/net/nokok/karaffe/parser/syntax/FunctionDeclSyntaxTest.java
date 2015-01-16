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
    public void testFuncDeclBody_Pattern() {
        testCode("def func : Int -> Int\n"
                + "func x = undefined\n");
    }

    @Test
    public void testReturnFunctionType() {
        testCode("def func : (Int -> ((String -> Int) -> Int)) = undefined\n");
    }

    @Test
    public void testVoidReturnType() {
        testCode("def func : (() -> ()) = undefined\n");
    }

    @Test
    public void testFuncDecl1() {
        testCode("def func : Int -> Int = undefined");
    }

    @Test
    public void testFuncDecl2() {
        testCode("def func : x:Int -> Int = undefined");
    }

    @Test
    public void testFuncDecl3() {
        testCode("def func : x:Int y:Int z:Int -> Int = undefined");
    }

    @Test
    public void testFuncDeclBody_VarDecl() {
        testCode("def func : Int -> Int\n"
                + "def a : Int = 0");
    }

    @Test
    public void testFuncDeclBody_MethodInvocation() {
        testCode("def func : Int -> Int\n"
                + "p()");
    }

    @Test
    public void testFuncDeclBody_Expr() {
        testCode("def func : Int -> Int\n"
                + "1");
    }

    @Test
    public void testFuncTypeVarargs() {
        testCode("def func : Int* -> Int\n = undefined");
    }

    @Test
    public void testFuncTypeVarargs1() {
        testCode("def func : Int Int -> Int = undefined");
    }
}
