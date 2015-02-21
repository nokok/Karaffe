/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Collections;
import java.util.Stack;
import java.util.function.Function;
import net.nokok.karaffe.parser.ASTAdd;
import net.nokok.karaffe.parser.ASTDecimalIntLiteral;
import net.nokok.karaffe.parser.ASTDiv;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTMul;
import net.nokok.karaffe.parser.ASTRem;
import net.nokok.karaffe.parser.ASTSub;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class InsnVisitor {

    private final Node node;

    private final InsnNodeItems items = new InsnNodeItems();

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        private final Stack<InsnNodeItem<?>> stack = new Stack<>();

        @Override
        public void visit(ASTDecimalIntLiteral node, Object data) throws ParserException {
            String tmp = node.jjtGetValue().toString();
            int value = Integer.valueOf(tmp);
            if (value <= Byte.MAX_VALUE) {
                items.add(new InsnNodeItem<>(new IntInsnNode(Opcodes.BIPUSH, value), Integer.class));
            } else if (value <= Short.MAX_VALUE) {
                items.add(new InsnNodeItem<>(new IntInsnNode(Opcodes.SIPUSH, value), Integer.class));
            } else {
                items.add(new InsnNodeItem<>(new LdcInsnNode(value), Integer.class));
            }
        }

        @Override
        public void visit(ASTAdd node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNodeItem<>(new InsnNode(Opcodes.IADD), Function.class));
        }

        @Override
        public void visit(ASTSub node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNodeItem<>(new InsnNode(Opcodes.ISUB), Function.class));
        }

        @Override
        public void visit(ASTMul node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNodeItem<>(new InsnNode(Opcodes.IMUL), Function.class));
        }

        @Override
        public void visit(ASTRem node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNodeItem<>(new InsnNode(Opcodes.IREM), Function.class));
        }

        @Override
        public void visit(ASTDiv node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNodeItem<>(new InsnNode(Opcodes.IDIV), Function.class));
        }

        @Override
        public void visit(ASTExprNode node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            pushAndClearStack();
        }

        private void pushAndClearStack() {
            Collections.reverse(stack);
            stack.forEach(items::add);
            stack.clear();
        }
    };

    public InsnVisitor(Node node) {
        this.node = node;
    }

    public InsnList getInsnList() {
        try {
            node.jjtAccept(visitor, this);
            InsnList list = new InsnList();
            items.stream().map(i -> i.getNode()).forEach(list::add);
            return list;
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Class<?> inferredClass() {
        return Object.class; //TODO:
    }
}
