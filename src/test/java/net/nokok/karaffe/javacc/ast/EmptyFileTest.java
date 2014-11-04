package net.nokok.karaffe.javacc.ast;

import org.junit.Test;

public class EmptyFileTest {

    @Test
    public void testEmptyFile() throws Exception {
        Program p = new KaraffeParser("").parse();
    }

    @Test(expected = ParseException.class)
    public void testNewLineFile() throws Exception {
        Program p = new KaraffeParser("\n").parse();
    }

    @Test
    public void testTabFile() throws Exception {
        Program p = new KaraffeParser("\t").parse();
    }

    @Test
    public void testWhiteSpaceFile() throws Exception {
        Program p = new KaraffeParser(" ").parse();
    }
}
