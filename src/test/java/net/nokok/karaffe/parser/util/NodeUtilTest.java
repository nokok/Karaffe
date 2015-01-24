/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.util.Optional;
import net.nokok.karaffe.parser.ASTClassBody;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTIdentifier;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class NodeUtilTest {

    @Test
    public void testFindFirstNode1() {
        ASTCompileUnit node = testCode("type Hoge {def this() = {}}");
        NodeUtil nodeUtil = new NodeUtil(node);
        Optional<ASTIdentifier> mId = nodeUtil.findFirstNode(ASTIdentifier.class);
        assertThat(mId.isPresent(), is(true));
        ASTIdentifier id = mId.get();
        assertThat(id.jjtGetValue().toString(), is("Hoge"));
        Optional<ASTClassBody> mayBeClassBody = nodeUtil.findFirstNode(ASTClassBody.class);
        assertThat(mayBeClassBody.isPresent(), is(true));
    }

}
