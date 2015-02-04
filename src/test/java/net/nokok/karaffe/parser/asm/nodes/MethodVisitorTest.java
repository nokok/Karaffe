/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class MethodVisitorTest {

    @Test
    public void testMethodNode() {
        ASTCompileUnit compileUnit = testCode("type Hoge{func hoge(x Int,y Int){}}");
        NodeUtil nodeUtil = new NodeUtil(compileUnit);
        ASTFuncDecl funcDecl = nodeUtil.forceGetFindFirstNode(ASTFuncDecl.class);
        MethodVisitor methodVisitor = new MethodVisitor(funcDecl, new ClassResolver());
        MethodNode methodNode = methodVisitor.getMethodNode();
        assertThat(methodNode.access, is(0));
        assertThat(methodNode.name, is("hoge"));
        assertThat(methodNode.desc, is(Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Integer.class), Type.getType(Integer.class))));
        assertThat(methodNode.exceptions.isEmpty(), is(true));
        InsnList insnList = methodNode.instructions;
        assertThat(insnList.size(), is(1));
        InsnNode retNode = (InsnNode) insnList.get(0);
        assertThat(retNode.getOpcode(), is(Opcodes.RETURN));
    }

    @Test
    public void testMethodDescriptor1() {
        ASTFuncDecl funcDecl = new NodeUtil(testCode("type Hoge{func hoge(x Int){}}")).forceGetFindFirstNode(ASTFuncDecl.class);
        MethodVisitor methodVisitor = new MethodVisitor(funcDecl, new ClassResolver());
        assertThat(methodVisitor.getMethodNode().desc, is(Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(Integer.class))));
    }

    @Test
    public void testMethodDescriptor2() {
        ASTFuncDecl funcDecl = new NodeUtil(testCode("type Hoge{func hoge(){}}")).forceGetFindFirstNode(ASTFuncDecl.class);
        MethodVisitor methodVisitor = new MethodVisitor(funcDecl, new ClassResolver());
        assertThat(methodVisitor.getMethodNode().desc, is(Type.getMethodDescriptor(Type.VOID_TYPE)));
    }

}
