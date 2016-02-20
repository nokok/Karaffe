package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class MinusAsgnExpr implements Expression {

    private final Expression e;
    private final Identifier target;

    public MinusAsgnExpr(Identifier target, Expression e) {
        this.target = target;
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        return new InsnList();
    }

}
