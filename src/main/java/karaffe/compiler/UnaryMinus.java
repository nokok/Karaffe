package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;

public class UnaryMinus implements Expression {

    private final Expression e;

    UnaryMinus(Expression e) {
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(new InsnNode(Opcodes.INEG));
        insnList.add(e.toNode());
        return insnList;
    }

    @Override
    public String toString() {
        return "(unary-minus " + e + ")";
    }

}
