package karaffe.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;

class IfExpr implements Expression {

    private final List<NodeGeneratable<?>> b1;
    private final List<NodeGeneratable<?>> b2;
    private final Expression e;

    public IfExpr(Expression e, List<NodeGeneratable<?>> b1, List<NodeGeneratable<?>> b2) {
        this.e = e;
        this.b1 = b1;
        this.b2 = b2;
    }

    IfExpr(Expression e, Expression b1) {
        this.e = e;
        List<NodeGeneratable<?>> b1list = new ArrayList<>();
        b1list.add(b1);
        this.b1 = b1list;
        this.b2 = Collections.emptyList();
    }

    IfExpr(Expression e, List<NodeGeneratable<?>> b1) {
        this.e = e;
        this.b1 = b1;
        this.b2 = Collections.emptyList();
    }

    IfExpr(Expression e, Expression b1, Expression b2) {
        this.e = e;

        List<NodeGeneratable<?>> b1list = new ArrayList<>();
        List<NodeGeneratable<?>> b2list = new ArrayList<>();

        b1list.add(b1);
        b2list.add(b2);

        this.b1 = b1list;
        this.b2 = b2list;
    }

    @Override
    public Class<?> inferredType() {
        return Object.class;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(e.toNode());
        LabelNode labelNode = new LabelNode();
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                        Type.getInternalName(Boolean.class),
                                        "booleanValue",
                                        Type.getMethodDescriptor(Type.BOOLEAN_TYPE),
                                        false));
        insnList.add(new JumpInsnNode(Opcodes.IFEQ, labelNode));
        for ( NodeGeneratable<?> g : b1 ) {
            Object node = g.toNode();
            if ( node instanceof AbstractInsnNode ) {
                insnList.add(AbstractInsnNode.class.cast(node));
            } else if ( node instanceof InsnList ) {
                insnList.add(InsnList.class.cast(node));
            } else {
                throw new RuntimeException(node.getClass().toString());
            }
        }
        LabelNode end = new LabelNode();
        insnList.add(new JumpInsnNode(Opcodes.GOTO, end));
        insnList.add(labelNode);
        if ( b2.isEmpty() ) {
            insnList.add(new InsnNode(Opcodes.NOP));
        }
        for ( NodeGeneratable<?> g : b2 ) {
            Object node = g.toNode();
            if ( node instanceof AbstractInsnNode ) {
                insnList.add(AbstractInsnNode.class.cast(node));
            } else if ( node instanceof InsnList ) {
                insnList.add(InsnList.class.cast(node));
            } else {
                throw new RuntimeException(node.getClass().toString());
            }
        }
        insnList.add(end);
        return insnList;
    }

    @Override
    public String toString() {
        return "(if-expr " + e.toString() + "(then " + b1.toString() + ") (else: " + b2.toString() + "))";
    }

}
