/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Collections;
import java.util.Stack;
import lombok.libs.org.objectweb.asm.Opcodes;
import net.nokok.karaffe.parser.ASTAdd;
import net.nokok.karaffe.parser.ASTDecimalIntLiteral;
import net.nokok.karaffe.parser.ASTDiv;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTMul;
import net.nokok.karaffe.parser.ASTRem;
import net.nokok.karaffe.parser.ASTSub;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LdcInsnNode;

public class InsnVisitor {

    private final Node node;

    private final InsnList insnList = new InsnList();

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        private final Stack<AbstractInsnNode> stack = new Stack<>();

        @Override
        public Object visit(ASTExpression node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            return null;
        }

        @Override
        public Object visit(ASTDecimalIntLiteral node, Object data) throws ParserException {
            String tmp = node.jjtGetValue().toString();
            int value = Integer.valueOf(tmp);
            if (value <= Byte.MAX_VALUE) {
                insnList.add(new IntInsnNode(Opcodes.BIPUSH, value));
            } else if (value <= Short.MAX_VALUE) {
                insnList.add(new IntInsnNode(Opcodes.SIPUSH, value));
            } else {
                insnList.add(new LdcInsnNode(value));
            }
            return null;
        }

        @Override
        public Object visit(ASTAdd node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNode(Opcodes.IADD));
            return null;
        }

        @Override
        public Object visit(ASTSub node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNode(Opcodes.ISUB));
            return null;
        }

        @Override
        public Object visit(ASTMul node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNode(Opcodes.IMUL));
            return null;
        }

        @Override
        public Object visit(ASTRem node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNode(Opcodes.IREM));
            return null;
        }

        @Override
        public Object visit(ASTDiv node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            stack.add(new InsnNode(Opcodes.IDIV));
            return null;
        }

        @Override
        public Object visit(ASTExprNode node, Object data) throws ParserException {
            node.childrenAccept(visitor, data);
            pushAndClearStack();
            return null;
        }

        private void pushAndClearStack() {
            Collections.reverse(stack);
            stack.forEach(insnList::add);
            stack.clear();
        }
    };

    public InsnVisitor(Node node) {
        this.node = node;
    }

    public InsnList getInsnList() {
        try {
            node.jjtAccept(visitor, this);
            return insnList;
        } catch (ParserException ex) {
            throw new RuntimeException(ex);
        }
    }

}
