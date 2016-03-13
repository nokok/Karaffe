package karaffe.compiler;

import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;

class WhileExpr implements Expression {

    private final Expression e;
    private final List<NodeGeneratable<?>> l;

    public WhileExpr(Expression e, Expression l) {
        this.e = e;
        List<NodeGeneratable<?>> list = new ArrayList<>(1);
        list.add(l);
        this.l = list;
    }

    WhileExpr(Expression e, List<NodeGeneratable<?>> l) {
        this.e = e;
        this.l = l;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        LabelNode before = new LabelNode();
        insnList.add(before);
        insnList.add(e.toNode());
        insnList.add(new MethodInsnNode(org.objectweb.asm.Opcodes.INVOKEVIRTUAL,
                                        Type.getInternalName(Boolean.class),
                                        "booleanValue",
                                        Type.getMethodDescriptor(Type.BOOLEAN_TYPE),
                                        false));
        LabelNode after = new LabelNode();
        insnList.add(new JumpInsnNode(Opcodes.IFEQ, after));
        l.stream()
            .map(t -> t.toNode())
            .forEach(t -> {
                if ( t instanceof InsnList ) {
                    insnList.add((InsnList) t);
                } else {
                    insnList.add((AbstractInsnNode) t);
                }
            });
        insnList.add(new JumpInsnNode(Opcodes.GOTO, before));
        insnList.add(after);
        return insnList;
    }

    @Override
    public String toString() {
        return "(while-expr cond:" + e + " body:" + l + ")";
    }

}
