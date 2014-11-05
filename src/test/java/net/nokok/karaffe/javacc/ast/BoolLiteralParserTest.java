package net.nokok.karaffe.javacc.ast;

import static net.nokok.karaffe.javacc.ast.LiteralTestUtil.tryValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class BoolLiteralParserTest {

    @Test
    public void testTrue() throws ParseException {
        assertThat(tryValue("true", BoolLiteral.class), is(true));
    }

    @Test
    public void testFalse() throws ParseException {
        assertThat(tryValue("false", BoolLiteral.class), is(true));
    }

    @Test(expected = ParseException.class)
    public void testInvalidTrue1() throws ParseException {
        tryValue("True", BoolLiteral.class);
    }

    @Test(expected = ParseException.class)
    public void testInvalidTrue2() throws ParseException {
        tryValue("TRUE", BoolLiteral.class);
    }

    @Test
    public void testInvalidTrue3() throws ParseException {
        assertThat(tryValue("1", BoolLiteral.class), is(false));
    }

    @Test(expected = ParseException.class)
    public void testInvalidFalse1() throws ParseException {
        tryValue("False", BoolLiteral.class);
    }

    @Test(expected = ParseException.class)
    public void testInvalidFalse2() throws ParseException {
        assertThat(tryValue("FALSE", BoolLiteral.class), is(false));
    }

    @Test
    public void testInvalidFalse3() throws ParseException {
        assertThat(tryValue("0", BoolLiteral.class), is(false));
    }

}
