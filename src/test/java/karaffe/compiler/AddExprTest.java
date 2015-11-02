package karaffe.compiler;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class AddExprTest {

    @Test
    public void testToString() {
        AddExpr expr = new AddExpr(Expr.ident("a", 1, 2), Expr.ident("b", 3, 4), 5, 6, 7, 8);
        assertThat(expr.toString(), is("(+ (id a(pos 1,2))(pos 5,6)(id b(pos 3,4))(pos 7,8))"));
    }

}
