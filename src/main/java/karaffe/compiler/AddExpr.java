package karaffe.compiler;

import java.math.BigInteger;
import java.util.Map;
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
    public Expression leftExpr() {
        return e1;
    }

    @Override
    public Expression rightExpr() {
        return e2;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        Map<Expression, TypeElement> typeCheck = Context.INSTANCE.typeCheck(e1, e2);
        TypeElement type1 = typeCheck.get(e1);
        TypeElement type2 = typeCheck.get(e2);

        System.out.println("type1: " + type1);
        System.out.println("type2: " + type2);

        insnList.add(e1.toNode());
        insnList.add(e2.toNode());
        insnList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(BigInteger.class), "add", Type.getMethodDescriptor(Type.getType(BigInteger.class), Type.getType(BigInteger.class)), false));
        return insnList;
    }

    @Override
    public String toString() {
        return "(+ " + e1.toString() + e1Pos + e2.toString() + e2Pos + ")";
    }

}
