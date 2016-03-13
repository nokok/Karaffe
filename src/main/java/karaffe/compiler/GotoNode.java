package karaffe.compiler;

import java.util.Objects;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

class GotoNode implements Statement, NodeGeneratable<AbstractInsnNode> {

    private final NodeGeneratable<?> l;
    private final LabelNode labelNode;
    private String path;

    public GotoNode(NodeGeneratable<?> l) {
        this.l = l;
        if ( l.toNode() instanceof LabelNode ) {
            this.labelNode = (LabelNode) l.toNode();
        } else {
            throw new RuntimeException();
        }
    }

    public void setPath(String path) {
        if ( this.path != null ) {
            throw new IllegalStateException();
        }
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public String getPath() {
        return path;
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
