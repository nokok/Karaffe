package net.nokok.karaffe.javacc.ast;

import static net.nokok.karaffe.javacc.ast.LiteralTestUtil.tryValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ArrayLiteralParserTest {

    @Test
    public void testEmptyArrayLiteral() throws ParseException {
        isValidArrayLiteral("[]");
    }

    @Test
    public void testIntArray1() throws ParseException {
        isValidArrayLiteral("[1]");
    }

    @Test
    public void testIntArray2() throws ParseException {
        isValidArrayLiteral("[1 2 3]");
    }

    @Test
    public void testFloatArray() throws ParseException {
        isValidArrayLiteral("[1.0 1.1 1.2]");
    }

    @Test
    public void testStringArray() throws ParseException {
        isValidArrayLiteral("[\"Hoge\" \"Fuga\" \"Piyo\"]");
    }

    @Test
    public void testNestedArray() throws ParseException {
        isValidArrayLiteral("[[1 2 3] [4 5 6] [7 8 9]]");
    }

    private void isValidArrayLiteral(String value) throws ParseException {
        assertThat(tryValue(value, FixedSizeArrayLiteral.class), is(true));
    }
}
