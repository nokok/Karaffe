package net.nokok.karaffe.javacc;

import static net.nokok.karaffe.javacc.KaraffeParserCommentTest.runKaraffeParserWithSource;
import org.junit.Test;

public class KaraffeParserBoolLiteralTest {

    @Test
    public void testBoolLiteral() throws Exception {
        runKaraffeParserWithSource("x = true\n");
        runKaraffeParserWithSource("x = false\n");
    }

    @Test(expected = ParseException.class)
    public void testUpperCaseTrueLiteral() throws Exception {
        runKaraffeParserWithSource("x = True\n");
    }

    @Test(expected = ParseException.class)
    public void testUpperCaseFalseLiteral() throws Exception {
        runKaraffeParserWithSource("x = False\n");
    }
}
