/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.syntax;

import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FunctionDeclSyntaxTest {

    @Test
    public void testSimpleFunctionDeclSyntax() {
        testFunc("def func(x:Int, y:Int):Int = {\n"
                 + "undefined\n"
                 + "}\n");
    }

    @Test
    public void testFunctionDeclVarargs() {
        testFunc("def func(x:Int, y:Int,z:Int*):Int = {\n"
                 + "undefined\n"
                 + "}");
    }

    @Test
    public void testCurriedFunc() {
        testFunc("def func(x:Int): Int -> Int = {\n"
                 + "undefined\n"
                 + "}");
    }

    @Test
    public void testCurriedFunc1() {
        testFunc("def func[T1,T2,R](x:T1, y:T2):T1 -> T2 -> R ={\n"
                 + "undefined\n"
                 + "}");
    }

    @Test
    public void testVarargs() {
        testFunc("def func(x:Int*):Int = {\n"
                 + "}");
    }

    @Test
    public void testAbstractFunc1() {
        testFunc("def func()");
    }

    @Test
    public void testAbstractFunc2() {
        testFunc("def func(x:Int)");
    }

    @Test
    public void testAbstractFunc3() {
        testFunc("def func(x:Int):ReturnType");
    }

    private void testFunc(String code) {
        testCode("type Type{\n"
                 + code
                 + "}");
    }
}
