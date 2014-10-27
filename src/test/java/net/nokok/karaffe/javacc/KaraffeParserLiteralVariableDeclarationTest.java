package net.nokok.karaffe.javacc;

import static net.nokok.karaffe.javacc.KaraffeParserCommentTest.runKaraffeParserWithSource;
import net.nokok.karaffe.javacc.stmt.*;
import net.nokok.karaffe.javacc.type.UndefinedValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KaraffeParserLiteralVariableDeclarationTest {

    @Test
    public void testVariableDeclaration() throws Exception {
        Statements statements = runKaraffeParserWithSource("x = true\n");
        assertThat(statements.size(), is(1));
        assertThat(statements.get(0).getType(), is(StatementType.VARIABLE_DECLARATION));
        VariableDeclaration v = (VariableDeclaration) statements.get(0);
        assertThat(v.variableName(), is("x"));
        assertThat(v.evalExpr(), is(true));
    }

    @Test
    public void testUndefinedVariable() throws Exception {
        Statements statements = runKaraffeParserWithSource("x = undefined\n");
        assertThat(statements.size(), is(1));
        assertThat(statements.get(0).getType(), is(StatementType.VARIABLE_DECLARATION));
        VariableDeclaration v = (VariableDeclaration) statements.get(0);
        assertThat(v.variableName(), is("x"));
        assertThat(v.evalExpr(), is(UndefinedValue.VALUE));
    }

    @Test
    public void testVariableDeclarationType() throws Exception {
        Statements statements = runKaraffeParserWithSource("x : Int = undefined\n");
        assertThat(statements.size(), is(1));
        assertThat(statements.get(0).getType(), is(StatementType.VARIABLE_DECLARATION));
    }

    @Test
    public void testVariableName() throws Exception {
        runKaraffeParserWithSource("a = undefined\n");
        runKaraffeParserWithSource("abcdefghijklmnopqrstuvwxyz = undefined\n");
        runKaraffeParserWithSource("a1 = undefined\n");
        runKaraffeParserWithSource("b101010 = undefined\n");
    }

    @Test(expected = TokenMgrError.class)
    public void testInvalidVariableName1() throws Exception {
        runKaraffeParserWithSource("_ = undefined\n");
    }

    /**
     * 変数名は小文字のa〜zではじめなければならない
     *
     * @throws Exception
     */
    @Test(expected = ParseException.class)
    public void testInvalidVariableName2() throws Exception {
        runKaraffeParserWithSource("1 = undefined\n");
    }

    /**
     * 大文字の変数は宣言できない
     *
     * @throws Exception
     */
    @Test(expected = ParseException.class)
    public void testInvalidVariableName3() throws Exception {
        runKaraffeParserWithSource("Hoge = undefined\n");
    }
}
