package karaffe.compiler;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

class GotoNode implements Statement, NodeGeneratable<AbstractInsnNode> {

    private final NodeGeneratable<?> l;
    private final LabelNode labelNode;

    public GotoNode(NodeGeneratable<?> l) {
        this.l = l;
        if ( l.toNode() instanceof LabelNode ) {
            this.labelNode = (LabelNode) l.toNode();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Class<?> inferredType() {
        return Object.class;
    }

    @Override
    public AbstractInsnNode toNode() {
        return new JumpInsnNode(Opcodes.GOTO, labelNode);
    }

    public LabelNode getLabelNode() {
        return labelNode;
    }

    @Override
    public String toString() {
        return "(goto " + l + ")";
    }

}
