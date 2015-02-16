/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.asm.nodes.InsnVisitor;
import net.nokok.karaffe.parser.asm.nodes.NodeUtil;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

@SuppressWarnings("unused")
public class InfererTest {
//    実装中
//    @Test
//    public void testInferer() {
//        ASTCompileUnit compileUnit = testCode("type D{def a = 0}");
//        InsnVisitor insnVisitor = new InsnVisitor(new NodeUtil(compileUnit).forceGetFindFirstNode(ASTExpression.class));
//        assertThat(insnVisitor.inferredClass(), IsInstanceOf.instanceOf(Integer.class));
//    }
}
