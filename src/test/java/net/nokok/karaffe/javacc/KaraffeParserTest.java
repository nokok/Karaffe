package net.nokok.karaffe.javacc;

import java.io.StringReader;
import static org.junit.Assert.fail;
import org.junit.Test;

public class KaraffeParserTest {

    @Test
    public void singleLineComment() {
        runKaraffeParserWithSource("//hooge\n");
    }

    @Test(expected = TokenMgrError.class)
    public void invalidSingleLineComment() {
        runKaraffeParserWithSource("/hoge\n");
    }

    @Test(expected = TokenMgrError.class)
    public void invalidBlockComment() {
        runKaraffeParserWithSource("/*\n\n\n\n\n/");
    }

    @Test
    public void blockComment() {
        runKaraffeParserWithSource("/**/");
        runKaraffeParserWithSource("/* hogehoge */");
        runKaraffeParserWithSource("/****/");
        runKaraffeParserWithSource("/*\n\n\n\n\n\n\n*/");
        runKaraffeParserWithSource("/*\r\r\r\r\r\r\r*/");
        runKaraffeParserWithSource("/*\r\n\r\n\r\r\n*/");
    }

    private void runKaraffeParserWithSource(String karaffeSrc) {
        KaraffeParser parser = new KaraffeParser(new StringReader(karaffeSrc));
        try {
            parser.enable_tracing();
            parser.start();
        } catch (ParseException ex) {
            fail(ex.getMessage());
        }
    }

}
