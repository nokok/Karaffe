package net.nokok.karaffe.javacc;

import static net.nokok.karaffe.javacc.KaraffeParserCommentTest.runKaraffeParserWithSource;
import net.nokok.karaffe.javacc.stmt.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class KaraffeParserSideEffectTypeAliasTest {

    @Test
    public void testSideEffectTypeAlias() throws Exception {
        Statements statements = runKaraffeParserWithSource("seffect type Any\n\ntype Hoge\n\n");
        assertThat(statements.size(), is(4));
        assertThat(statements.get(0).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(1).getType(), is(StatementType.SCOPE_SPLITTER));
        assertThat(statements.get(2).getType(), is(StatementType.TYPE_ALIAS));
        assertThat(statements.get(3).getType(), is(StatementType.SCOPE_SPLITTER));
    }
}
