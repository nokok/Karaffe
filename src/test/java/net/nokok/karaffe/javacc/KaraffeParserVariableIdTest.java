package net.nokok.karaffe.javacc;

import static net.nokok.karaffe.javacc.KaraffeParserCommentTest.runKaraffeParserWithSource;
import org.junit.Test;

public class KaraffeParserVariableIdTest {

    @Test
    public void testVariableId() throws Exception {
        runKaraffeParserWithSource("x = undefined\n");
        runKaraffeParserWithSource("x123 = undefined\n");
        runKaraffeParserWithSource("x1 = undefined\n");
    }

    @Test(expected = ParseException.class)
    public void testInvalidVarId() throws Exception {
        runKaraffeParserWithSource("1x = undefined\n");
    }
}
