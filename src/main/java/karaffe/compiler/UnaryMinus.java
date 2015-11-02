package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class UnaryMinus implements Expression {

    private final Expression e;
    private final Position pos;

    UnaryMinus(Expression e, int eleft, int eright) {
        this.e = e;
        this.pos = new Position(eleft, eright);
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(new InsnNode(Opcodes.INEG));
        insnList.add(e.toNode());
        return insnList;
    }

    @Override
    public Class<?> inferredType() {
        return e.inferredType();
    }

    @Override
    public String toString() {
        return "(unary-minus " + e + pos + ")";
    }

}
