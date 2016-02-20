package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class Argument implements NodeGeneratable<InsnList> {

    private final Expression e;

    Argument(Expression e) {
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        return e.toNode();
    }

    @Override
    public String toString() {
        return "(arg " + e.toString() + ")";
    }

}
