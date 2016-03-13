package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class AndExpr implements Expression {

    private final Expression e1;
    private final Expression e2;

    public AndExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public InsnList toNode() {
        return new InsnList();
    }

}
