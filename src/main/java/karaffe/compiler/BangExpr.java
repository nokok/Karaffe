package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class BangExpr implements Expression {

    private final Expression e;

    public BangExpr(Expression e) {
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        return new InsnList();
    }

}
