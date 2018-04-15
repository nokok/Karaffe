package ut;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.ast.api.Tree;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.IntLiteral;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

public class ExprTest {

    @Test
    public void testExpr() {
        // 1 + 2
        assertEquals("1.+(2)", getString(
                new Apply(
                        new IntLiteral(1),
                        new SimpleName("+"),
                        new IntLiteral(2))));

        // target.method(arg1, arg2)
        assertEquals("target.method(arg1, arg2)", getString(
                new Apply(
                        new ExpressionName("target"),
                        new SimpleName("method"),
                        new ExpressionName("arg1"),
                        new ExpressionName("arg2"))));

        // staticMethod()
        assertEquals("this.staticMethod()", getString(new Apply(new SimpleName("staticMethod"))));

    }

    private String getString(Tree tree) {
        return tree.toString();
    }
}
