/**
 * Karaffe Programming Language
 */
package karaffe.compiler.syntax;

import static karaffe.compiler.syntax.KaraffeParserSyntaxTest.testCode;
import org.junit.Test;

public class FunctionDeclSyntaxTest {

    @Test
    public void testSimpleFunctionDeclSyntax() {
        testFunc("func hoge(x Int, y Int) Int {\n"
                + "undefined\n"
                + "}\n");
    }

    @Test
    public void testFunctionDeclVarargs() {
        testFunc("func hoge(x Int, y Int,z Int*) Int{\n"
                + "undefined\n"
                + "}");
    }

    @Test
    public void testCurriedFunc() {
        testFunc("func hoge(x Int)  Int -> Int {\n"
                + "undefined\n"
                + "}");
    }

    @Test
    public void testCurriedFunc1() {
        testFunc("func hoge[T1,T2,R](x T1, y T2) T1 -> T2 -> R {\n"
                + "undefined\n"
                + "}");
    }

    @Test
    public void testVarargs() {
        testFunc("func hoge(x Int*) Int {\n"
                + "}");
    }

    @Test
    public void testAbstractFunc1() {
        testFunc("func hoge()");
    }

    @Test
    public void testAbstractFunc2() {
        testFunc("func hoge(x Int)");
    }

    @Test
    public void testAbstractFunc3() {
        testFunc("func hoge(x Int) ReturnType");
    }

    @Test
    public void testExprBodyFunc() {
        testFunc("func hoge(x Int,y Int) = x + y");
    }

    private void testFunc(String code) {
        testCode("type Type{\n"
                + code
                + "}");
    }
}
