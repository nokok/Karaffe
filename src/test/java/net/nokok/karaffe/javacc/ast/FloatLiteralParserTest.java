package net.nokok.karaffe.javacc.ast;

import static net.nokok.karaffe.javacc.ast.LiteralTestUtil.createProgramFromValue;
import org.junit.Test;

public class FloatLiteralParserTest {

    @Test
    public void testFloatLiteral1() throws ParseException {
        Program p = createProgramFromValue("5.0");
    }

    @Test
    public void testFloatLiteral2() throws ParseException {
        createProgramFromValue("0.0");
    }

    @Test
    public void testFloatLiteral3() throws ParseException {
        createProgramFromValue("0.000005");
    }

    @Test
    public void testFloatLiteral4() throws ParseException {
        createProgramFromValue("123.01");
    }

    @Test
    public void testFloatLiteral5() throws ParseException {
        createProgramFromValue("1.3e4");
    }

    @Test
    public void testFloatLiteral6() throws ParseException {
        createProgramFromValue("0.5e4");
    }

    @Test
    public void testFloatLiteral7() throws ParseException {
        createProgramFromValue("1.3E4");
    }

    @Test
    public void testFloatLiteral8() throws ParseException {
        createProgramFromValue("0.5E4");
    }

    @Test
    public void testFloatLiteral9() throws ParseException {
        createProgramFromValue("-0.5");
    }

    @Test
    public void testFloatLiteral10() throws ParseException {
        createProgramFromValue("-5.0");
    }

    @Test
    public void testFloatLiteral11() throws ParseException {
        createProgramFromValue("-12.0");
    }

    @Test
    public void testFloatLiteral12() throws ParseException {
        createProgramFromValue("-12.12345");
    }

    @Test
    public void testFloatLiteral13() throws ParseException {
        createProgramFromValue("-3.2e5");
    }

    @Test
    public void testFloatLiteral14() throws ParseException {
        createProgramFromValue("-3.2e-5");
    }

    @Test
    public void testFloatLiteral15() throws ParseException {
        createProgramFromValue("-3.2E-5");
    }

    @Test
    public void invalidFloatLiteral16() throws ParseException {
        createProgramFromValue("0.000");
    }

    @Test(expected = TokenMgrError.class)
    public void invalidFloatLiteral1() throws ParseException {
        createProgramFromValue(".05");
    }

}
