package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

class AssignmentExpr implements Expression {

    private final Identifier target;
    private final Expression e;

    public AssignmentExpr(Identifier target, Expression e) {
        this.target = target;
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        insnList.add(e.toNode());
        int index = Context.INSTANCE.findLocalVarIndex(target.getPath());
        insnList.add(new VarInsnNode(Opcodes.ASTORE, index));
        return insnList;
    }

    @Override
    public String toString() {
        return "(assignment-expr target:" + target + " expr:" + e + ")";
    }

}
