package net.nokok.karaffe.javacc;

import org.junit.Test;

public class KaraffeParserTest {

    @Test
    public void testSingleLineComment() throws Exception {
        runKaraffeParserWithSource("// type hogehoge = hogehogehoge\n");
    }

    @Test
    public void testBlockComment() throws Exception {
        Statements program = runKaraffeParserWithSource("/* \n"
                                                        + "type Hoge = Hogehoge\n"
                                                        + "*/\n");
        program.size()
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

    @Test
    public void testNotFoundType() throws Exception {
        runKaraffeParserWithSource("type Foo = InvalidTypename\n\n");
    }

    @Test(expected = ParseException.class)
    public void testMissingNewLine1() throws Exception {
        runKaraffeParserWithSource("type Foo type Hoge\n\n");
        //                                  ^ missing newline
    }

    private Statements runKaraffeParserWithSource(String karaffeSrc) throws Exception {
        KaraffeParser parser = KaraffeParser.createParser(karaffeSrc);
        return parser.start();
    }

}
