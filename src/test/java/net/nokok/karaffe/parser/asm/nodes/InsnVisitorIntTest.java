/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.ASTFieldInitializer;
import static net.nokok.karaffe.parser.syntax.KaraffeParserSyntaxTest.testCode;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class InsnVisitorIntTest {

    @Test
    public void testBipush() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 1}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(1));
        IntInsnNode one = (IntInsnNode) insnList.get(0);
        assertThat(one.operand, is(1));
        assertThat(one.getOpcode(), is(Opcodes.BIPUSH));
    }

    @Test
    public void testSipush() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = " + Short.MAX_VALUE + "}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(1));
        IntInsnNode shortMax = (IntInsnNode) insnList.get(0);
        assertThat(shortMax.operand, is(32767)); //int
        assertThat(shortMax.getOpcode(), is(Opcodes.SIPUSH));
    }

    @Test
    public void testLdcPush() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 100000}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(1));
        LdcInsnNode ldc = (LdcInsnNode) insnList.get(0);
        assertThat(ldc.cst, is(100000));
        assertThat(ldc.getOpcode(), is(Opcodes.LDC));
    }

    @Test
    public void testIntInsn1() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 1 + 2 + 3}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(5));
        IntInsnNode one = (IntInsnNode) insnList.get(0);
        IntInsnNode two = (IntInsnNode) insnList.get(1);
        InsnNode iadd = (InsnNode) insnList.get(2);
        IntInsnNode three = (IntInsnNode) insnList.get(3);
        InsnNode iadd1 = (InsnNode) insnList.get(4);
        assertThat(one.operand, is(1));
        assertThat(two.operand, is(2));
        assertThat(iadd.getOpcode(), is(Opcodes.IADD));
        assertThat(three.operand, is(3));
        assertThat(iadd1.getOpcode(), is(Opcodes.IADD));
    }

    @Test
    public void testIntInsn2() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 1 + 2 - 3}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(5));
        //1 + 2 - 3
        //bipush 1
        IntInsnNode one = (IntInsnNode) insnList.get(0);
        //bipush 2
        IntInsnNode two = (IntInsnNode) insnList.get(1);
        //iadd
        InsnNode iadd = (InsnNode) insnList.get(2);
        //bipush 3
        IntInsnNode three = (IntInsnNode) insnList.get(3);
        //isub
        InsnNode isub = (InsnNode) insnList.get(4);
        assertThat(one.operand, is(1));
        assertThat(two.operand, is(2));
        assertThat(three.operand, is(3));
        assertThat(iadd.getOpcode(), is(Opcodes.IADD));
        assertThat(isub.getOpcode(), is(Opcodes.ISUB));
    }

    @Test
    public void testIntInsn3() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 1 + 2 - 3 + 4}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        assertThat(insnList.size(), is(7));
        //1 + 2 - 3 + 4
        //bipush 1
        IntInsnNode one = (IntInsnNode) insnList.get(0);
        //bipush 2
        IntInsnNode two = (IntInsnNode) insnList.get(1);
        //iadd
        InsnNode iadd = (InsnNode) insnList.get(2);
        //bipush 3
        IntInsnNode three = (IntInsnNode) insnList.get(3);
        //isub
        InsnNode isub = (InsnNode) insnList.get(4);
        //bipush 4
        IntInsnNode four = (IntInsnNode) insnList.get(5);
        //iadd
        InsnNode iadd1 = (InsnNode) insnList.get(6);
    }

    @Test
    public void testIntInsn4() {
        NodeUtil nodeUtil = new NodeUtil(testCode("type H{def a = 1 + 2 * 3}"));
        ASTFieldInitializer fieldInitializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
        InsnVisitor insnVisitor = new InsnVisitor(fieldInitializer);
        InsnList insnList = insnVisitor.getInsnList();
        IntInsnNode one = (IntInsnNode) insnList.get(0);
        assertThat(one.operand, is(1));
        IntInsnNode two = (IntInsnNode) insnList.get(1);
        assertThat(two.operand, is(2));
        IntInsnNode three = (IntInsnNode) insnList.get(2);
        assertThat(three.operand, is(3));
        InsnNode imul = (InsnNode) insnList.get(3);
        assertThat(imul.getOpcode(), is(Opcodes.IMUL));
        InsnNode iadd = (InsnNode) insnList.get(4);
        assertThat(iadd.getOpcode(), is(Opcodes.IADD));
    }
}
