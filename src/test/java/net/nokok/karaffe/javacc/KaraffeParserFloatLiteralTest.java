package net.nokok.karaffe.javacc;

import static net.nokok.karaffe.javacc.KaraffeParserCommentTest.runKaraffeParserWithSource;
import net.nokok.karaffe.javacc.stmt.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KaraffeParserFloatLiteralTest {

    @Test
    public void testFloatLiteral() throws Exception {
        Statements statements = runKaraffeParserWithSource("x = 1.3\n");
        assertThat(statements.size(), is(1));
        assertThat(statements.get(0).getType(), is(StatementType.VARIABLE_DECLARATION));
        VariableDeclaration v = (VariableDeclaration) statements.get(0);
        assertThat(v.variableName(), is("x"));
        assertThat(v.evalExpr(), is(1.3));
    }

    @Test
    public void testFLoatLiteral2() throws Exception {
        runKaraffeParserWithSource("x = 120.8\n");
        runKaraffeParserWithSource("x = -9.3\n");
        runKaraffeParserWithSource("x = -0.043\n");
        runKaraffeParserWithSource("x = -10.30\n");
        runKaraffeParserWithSource("x= -50.40\n");
        runKaraffeParserWithSource("x = 7.5e10\n");
        runKaraffeParserWithSource("x = 5.012e4\n");
        runKaraffeParserWithSource("x = 7.354e-6\n");
        runKaraffeParserWithSource("x = 1.33E5\n");
        runKaraffeParserWithSource("x = 1.33E-5\n");
        runKaraffeParserWithSource("x = 0.5\n");
    }

    @Test(expected = ParseException.class)
    public void testMinusZeroPointZero() throws Exception {
        runKaraffeParserWithSource("x = -0.0\n");
    }

    @Test(expected = ParseException.class)
    public void testInvalidFloatLiteral1() throws Exception {
        runKaraffeParserWithSource("x = 1.\n");
    }

    @Test(expected = ParseException.class)
    public void testInvalidFloatLiteral2() throws Exception {
        runKaraffeParserWithSource("x = .7\n");
    }
}
