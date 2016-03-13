package karaffe.compiler;

import org.objectweb.asm.tree.InsnList;

class LtExpr implements Expression {

    private final Expression e1;
    private final Expression e2;

    public LtExpr(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public InsnList toNode() {
        InsnList insnList = new InsnList();
        TypeElement e1Type = Context.INSTANCE.getType(e1);
        TypeElement e2Type = Context.INSTANCE.getType(e2);
        if ( !e1Type.equals(e2Type) ) {
            Context.INSTANCE.reportTypeError(null, e1Type.resolvedType(), e2Type.resolvedType());
        }
        insnList.add(e1.toNode());
        insnList.add(e2.toNode());
        return insnList;
    }

    @Override
    public String toString() {
        return "(lt(<) : " + e1 + e2 + ")";
    }

}
