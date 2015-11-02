package karaffe.compiler;

import org.objectweb.asm.tree.LabelNode;

class LabelNodeAST implements Statement, NodeGeneratable<LabelNode> {

    private final Identifier i;

    private final LabelNode labelNode = new LabelNode();

    public LabelNodeAST(Identifier i) {
        this.i = i;
    }

    @Override
    public Class<?> inferredType() {
        return Object.class;
    }

    @Override
    public LabelNode toNode() {
        return labelNode;
    }

    public Identifier get() {
        return i;
    }

    @Override
    public String toString() {
        return "(label-node " + i + ")";
    }

}
