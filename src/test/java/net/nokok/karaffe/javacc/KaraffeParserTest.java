package net.nokok.karaffe.javacc;

import net.nokok.karaffe.javacc.stmt.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KaraffeParserTest {

    @Test
    public void testSingleLineComment() throws Exception {
        Statements statements = runKaraffeParserWithSource("// type hogehoge = hogehogehoge\n");
        assertThat(statements.size(), is(0));
    }

    @Test
    public void testBlockComment() throws Exception {
        Statements program = runKaraffeParserWithSource("/* \n"
                                                        + " * type Hoge = Hogehoge\n"
                                                        + "*/\n");
        //最後の改行でNewLineStatementとなっている
        assertThat(program.size(), is(1));
        assertThat(program.get(0).getType(), is(StatementType.SCOPE_SPLITTER));
    }

    @Test
    public void testBlockComment1() throws Exception {
        runKaraffeParserWithSource("/* */");
        Statements statements = runKaraffeParserWithSource("/* */ /* */");
        assertThat(statements.size(), is(0));
    }

    @Test
    public void testNestedBlockComment() throws Exception {
        Statements statements = runKaraffeParserWithSource("/* /* */ */ ");
        assertThat(statements.size(), is(0));
        statements = runKaraffeParserWithSource("/********/");
        assertThat(statements.size(), is(0));
        statements = runKaraffeParserWithSource("/* /* /*/*/* /*/*/* /*/*/* ** */ * / */*/*/*/ */*/*/ */*/*/");
        assertThat(statements.size(), is(0));
    }

    @Test
    public void testTypeAlias() throws Exception {
        Statements statements = runKaraffeParserWithSource("type Any\n\ntype Hoge\n\n");
        assertThat(statements.size(), is(4));
        assertThat(statements.get(0).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(1).getType(), is(StatementType.SCOPE_SPLITTER));
        assertThat(statements.get(2).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(3).getType(), is(StatementType.SCOPE_SPLITTER));

    }

    @Test
    public void testTypeAliasWithSpace() throws Exception {
        Statements statements = runKaraffeParserWithSource("type Any\n\ntype      Hoge\n\n");
        assertThat(statements.size(), is(4));
        assertThat(statements.get(0).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(1).getType(), is(StatementType.SCOPE_SPLITTER));
        assertThat(statements.get(2).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(3).getType(), is(StatementType.SCOPE_SPLITTER));
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
        Statements statements = runKaraffeParserWithSource("type Foo type Hoge\n\n");
        //                                                          ^ missing newline
        assertThat(statements.size(), is(3));
    }

    private Statements runKaraffeParserWithSource(String karaffeSrc) throws Exception {
        KaraffeParser parser = KaraffeParser.createParser(karaffeSrc);
        parser.enable_tracing();
        return parser.start();
    }

}
