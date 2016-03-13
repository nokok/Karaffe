package karaffe.compiler;

import karaffe.core.Int;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

class MulExpr implements Expression, BinaryExpression {

    private final Expression e1;
    private final Expression e2;
    private final Position e1Pos;
    private final Position e2Pos;

    MulExpr(Expression e1, Expression e2, int e1left, int e1right, int e2left, int e2right) {
        this.e1 = e1;
        this.e2 = e2;
        this.e1Pos = new Position(e1left, e1right);
        this.e2Pos = new Position(e2left, e2right);
    }

    @Override
    public Expression leftExpr() {
        return e1;
    }

    @Override
    public Expression rightExpr() {
        return e2;
    }

    @Override
    public InsnList toNode() {
        InsnList list = new InsnList();
        list.add(e1.toNode());
        list.add(e2.toNode());
        list.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(Int.class), "mul", Type.getMethodDescriptor(Type.getType(Int.class), Type.getType(Int.class)), false));
        return list;
    }

    @Override
    public String toString() {
        return "(* " + e1.toString() + e1Pos + e2.toString() + e2Pos + ")";
    }

}
