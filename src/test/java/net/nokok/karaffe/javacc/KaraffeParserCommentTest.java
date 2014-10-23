package net.nokok.karaffe.javacc;

import net.nokok.karaffe.javacc.stmt.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KaraffeParserCommentTest {

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

    public static Statements runKaraffeParserWithSource(String karaffeSrc) throws Exception {
        KaraffeParser parser = KaraffeParser.createParser(karaffeSrc);
        parser.enable_tracing();
        return parser.start();
    }

}
