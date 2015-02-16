/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.List;
import java.util.Optional;

import lombok.libs.org.objectweb.asm.Opcodes;
import net.nokok.karaffe.parser.ASTClassBody;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTFieldInitializer;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTIdentifier;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;

public class NodeUtilTest {

    @Test
    public void testFindFirstNode1() {
        ASTCompileUnit node = testCode("type Hoge {func this() {}}");
        NodeUtil nodeUtil = new NodeUtil(node);
        Optional<ASTIdentifier> mId = nodeUtil.findFirstNode(ASTIdentifier.class);
        assertThat(mId.isPresent(), is(true));
        ASTIdentifier id = mId.get();
        assertThat(id.jjtGetValue().toString(), is("Hoge"));
        Optional<ASTClassBody> mayBeClassBody = nodeUtil.findFirstNode(ASTClassBody.class);
        assertThat(mayBeClassBody.isPresent(), is(true));
    }

//    @Test
//    public void testClassNode() {
//        ASTCompileUnit compileUnit = testCode("type D{}");
//        NodeUtil nodeUtil = new NodeUtil(compileUnit);
//        Optional<List<ClassNode>> mayBeClassNode = nodeUtil.getClassNode();
//        assertThat(mayBeClassNode.isPresent(), is(true));
//        List<ClassNode> classNodes = mayBeClassNode.get();
//        assertThat(classNodes.size(), is(1));
//    }
    @Test
    public void testGetInsnList() {
        ASTCompileUnit compileUnit = testCode("type Hoge{def a = 0}");
        NodeUtil nodeUtil = new NodeUtil(compileUnit);
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        NodeUtil fieldInitializerNode = new NodeUtil(fieldInitializer);
        InsnList insnList = fieldInitializerNode.getInsnList();
        assertThat(insnList.size(), is(1));
        IntInsnNode insn = (IntInsnNode) insnList.get(0);
        assertThat(insn.getOpcode(), is(Opcodes.BIPUSH));
        assertThat(insn.operand, is(0));
    }

    @Test
    public void testCollectNodes() {
        ASTCompileUnit compileUnit = testCode("type Hoge{func hoge(){} func fuga(){} def a = 0}");
        NodeUtil nodeUtil = new NodeUtil(compileUnit);
        List<ASTFuncDecl> funcDecls = nodeUtil.collectNodes(ASTFuncDecl.class);
        assertThat(funcDecls.size(), is(2));
    }
}
