package net.nokok.karaffe.javacc.ast;

import static net.nokok.karaffe.javacc.ast.LiteralTestUtil.tryValue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IntLiteralParserTest {

    @Test
    public void testNaturalValue() throws ParseException {
        assertThat(tryValue("0", IntLiteral.class), is(true));
        assertThat(tryValue("1", IntLiteral.class), is(true));
    }

    @Test
    public void testNegativeValue1() throws ParseException {
        assertThat(tryValue("-1", IntLiteral.class), is(true));
    }

    @Test
    public void testNegativeValue2() throws ParseException {
        assertThat(tryValue("-50", IntLiteral.class), is(true));
    }

    @Test
    public void testHex1() throws ParseException {
        assertThat(tryValue("0xf", IntLiteral.class), is(true));
    }

    @Test
    public void testHex2() throws ParseException {
        assertThat(tryValue("-0xff", IntLiteral.class), is(true));
    }

    @Test
    public void testHex3() throws ParseException {
        assertThat(tryValue("0X30", IntLiteral.class), is(true));
    }

    @Test
    public void testHex4() throws ParseException {

    }

    private boolean isIntLiteral(VariableDeclaration node) {
        return node.getNode() instanceof IntLiteral;
    }
}
