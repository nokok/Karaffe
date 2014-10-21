package net.nokok.karaffe.javacc;

import java.io.StringReader;
import org.junit.Test;

public class KaraffeParserTest {

    @Test
    public void testSingleLineComment() throws Exception {
        runKaraffeParserWithSource("// type hogehoge = hogehogehoge\n");
    }

    @Test
    public void testBlockComment() throws Exception {
        runKaraffeParserWithSource("/* \n"
                                   + "type Hoge = Hogehoge\n"
                                   + "*/\n");
    }

    @Test
    public void testBlockComment1() throws Exception {
        runKaraffeParserWithSource("/* */");
        runKaraffeParserWithSource("/* */ /* */");
    }

    @Test
    public void testNestedBlockComment() throws Exception {
        runKaraffeParserWithSource("/* /* */ */ ");
        runKaraffeParserWithSource("/********/");
        runKaraffeParserWithSource("/* /* /*/*/* /*/*/* /*/*/* ** */ * / */*/*/*/ */*/*/ */*/*/");
    }

    @Test
    public void testTypeAlias() throws Exception {
        runKaraffeParserWithSource("type Any\n\ntype Hoge\n\n");
        runKaraffeParserWithSource("type Any\n\ntype      Hoge\n\n");
    }

    @Test
    public void testTypeAliasWithBaseType() throws Exception {
        runKaraffeParserWithSource("type Any\n\n"
                                   + "type Base\n\n"
                                   + "type FooBar = Base\n\n");
    }

    @Test
    public void testTypeAliasWithoutSpace() throws Exception {
        runKaraffeParserWithSource("type Any\n\n"
                                   + "type Base=Any\n\n");
    }

    @Test(expected = RuntimeException.class)
    public void testNotFoundType() throws Exception {
        runKaraffeParserWithSource("type Foo = InvalidTypename\n\n");
    }

    @Test(expected = ParseException.class)
    public void testMissingNewLine1() throws Exception {
        runKaraffeParserWithSource("type Foo type Hoge\n\n");
        //                                  ^ missing newline
    }

    @Test(expected = ParseException.class)
    public void testMissingNewLine2() throws Exception {
        runKaraffeParserWithSource("type Foo\n type Hoge\n\n");
        //                                  ^ missing newline
    }

    private void runKaraffeParserWithSource(String karaffeSrc) throws Exception {
        KaraffeParser parser = new KaraffeParser(new StringReader(karaffeSrc));
        parser.debug(karaffeSrc);
    }

}
