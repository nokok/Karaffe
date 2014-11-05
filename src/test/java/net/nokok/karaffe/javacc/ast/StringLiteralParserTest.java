package net.nokok.karaffe.javacc.ast;

import static net.nokok.karaffe.javacc.ast.LiteralTestUtil.tryValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class StringLiteralParserTest {

    @Test
    public void testStringLiteral() throws ParseException {
        assertThat(tryValue("\"StringValue\"", StringLiteral.class), is(true));
    }

    @Test
    public void testEmptyStringLiteral() throws ParseException {
        assertThat(tryValue("\"\"", StringLiteral.class), is(true));
    }

    @Test
    public void testBackSpace() throws ParseException {
        assertThat(tryValue("\"\\b\"", StringLiteral.class), is(true));
    }

    @Test
    public void testHTab() throws ParseException {
        assertThat(tryValue("\"\\t\"", StringLiteral.class), is(true));
    }

    @Test
    public void testNewLine() throws ParseException {
        assertThat(tryValue("\"\\n\"", StringLiteral.class), is(true));
    }

    @Test
    public void testCarriageReturn() throws ParseException {
        assertThat(tryValue("\"\\r\"", StringLiteral.class), is(true));
    }

    @Test
    public void testFormFeed() throws ParseException {
        assertThat(tryValue("\"\\f\"", StringLiteral.class), is(true));
    }

    @Test
    public void testSingleQuotation() throws ParseException {
        assertThat(tryValue("\"\\\'\"", StringLiteral.class), is(true));
    }

    @Test
    public void testDoubleQuotation() throws ParseException {
        assertThat(tryValue("\"\\\"\"", StringLiteral.class), is(true));
    }

    @Test
    public void testYenCharacter() throws ParseException {
        assertThat(tryValue("\"\\\\\"", StringLiteral.class), is(true));
    }

    @Test
    public void testUnicodeEscape1() throws ParseException {
        assertThat(tryValue("\"\\u0000\"", StringLiteral.class), is(true));
    }

    @Test
    public void testUnicodeEscape2() throws ParseException {
        assertThat(tryValue("\"\\uffff\"", StringLiteral.class), is(true));
    }

    @Test(expected = TokenMgrError.class)
    public void testInvalidStringLiteral1() throws ParseException {
        assertThat(tryValue("\"\n\"", StringLiteral.class), is(true));
    }

    @Test(expected = TokenMgrError.class)
    public void testInvalidEscapeSequence() throws ParseException {
        assertThat(tryValue("\"\\a\"", StringLiteral.class), is(true));
    }
}
