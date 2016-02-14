package karaffe.compiler;

import java.math.BigInteger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

class DivExpr implements Expression, BinaryExpression {

    private final Expression e1;
    private final Expression e2;
    private final Position e1Pos;
    private final Position e2Pos;

    DivExpr(Expression e1, Expression e2, int e1left, int e1right, int e2left, int e2right) {
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
    public Class<?> leftInferredType() {
        return e1.inferredType();
    }

    @Override
    public Class<?> rightInferredType() {
        return e2.inferredType();
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(e1.toNode());
        insnList.add(e2.toNode());
        insnList.add(new InsnNode(Opcodes.IDIV));
        return insnList;
    }

    @Override
    public Class<?> inferredType() {
        return BigInteger.class;
    }

    @Override
    public String toString() {
        return "(/ " + e1.toString() + e1Pos.toString() + e2.toString() + e2Pos.toString() + ")";
    }

}
