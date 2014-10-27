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
}
