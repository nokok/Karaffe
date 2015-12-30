package karaffe.compiler;

import java.util.Objects;
import org.objectweb.asm.tree.LabelNode;

class LabelNodeAST implements Statement, NodeGeneratable<LabelNode> {

    private final Identifier i;

    private final LabelNode labelNode = new LabelNode();

    private String path;

    public LabelNodeAST(Identifier i) {
        this.i = i;
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
