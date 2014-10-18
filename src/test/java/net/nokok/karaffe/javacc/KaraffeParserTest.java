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
        runKaraffeParserWithSource("type Any\ntype Hoge\n");
        runKaraffeParserWithSource("type Any\ntype      Hoge\n");
    }

    @Test
    public void testTypeAliasWithBaseType() throws Exception {
        runKaraffeParserWithSource("type Any\ntype Base\n"
                                   + "type FooBar = Base\n");
    }

    @Test
    public void testTypeAliasWithoutSpace() throws Exception {
        runKaraffeParserWithSource("type Any\n"
                                   + "type Base=Any\n");
    }

    @Test(expected = RuntimeException.class)
    public void testNotFoundType() throws Exception {
        runKaraffeParserWithSource("type Foo = InvalidTypename\n");
    }

    @Test(expected = ParseException.class)
    public void testMissingNewLine() throws Exception {
        runKaraffeParserWithSource("type Foo type Hoge\n");
        //                                  ^ missing newline
    }

    private void runKaraffeParserWithSource(String karaffeSrc) throws Exception {
        KaraffeParser parser = new KaraffeParser(new StringReader(karaffeSrc));
        parser.debug(karaffeSrc);
    }

}
