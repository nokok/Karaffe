package karaffe.compiler;

import java.math.BigInteger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

public class AddExpr implements Expression, BinaryExpression {

    private final Expression e1;
    private final Expression e2;
    private final Position e1Pos;
    private final Position e2Pos;

    public AddExpr(Expression e1, Expression e2, int e1Line, int e1Column, int e2Line, int e2Column) {
        this.e1 = e1;
        this.e2 = e2;
        this.e1Pos = new Position(e1Line, e1Column);
        this.e2Pos = new Position(e2Line, e2Column);
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(e1.toNode());
        insnList.add(e2.toNode());
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(BigInteger.class), "add", Type.getMethodDescriptor(Type.getType(BigInteger.class), Type.getType(BigInteger.class)), false));
        return insnList;
    }

    @Override
    public Class<?> inferredType() {
        return BigInteger.class;
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
    public String toString() {
        return "(+ " + e1.toString() + e1Pos + e2.toString() + e2Pos + ")";
    }

}
