package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class PlusAsgnExpr implements Expression {

    private final Expression e;
    private final Identifier target;

    public PlusAsgnExpr(Identifier target, Expression e) {
        this.target = target;
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        return new InsnList();
    }

}
