package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class CastExpr implements Expression {

    private final Expression e;
    private final Identifier to;

    public CastExpr(Expression e, Identifier to) {
        this.e = e;
        this.to = to;
    }

    @Override
    public InsnList toNode() {
        return new InsnList();
    }

}
