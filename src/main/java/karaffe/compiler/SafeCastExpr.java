package karaffe.compiler;

class SafeCastExpr implements Expression {

    private final Expression e;
    private final Identifier to;

    public SafeCastExpr(Expression e, Identifier to) {
        this.e = e;
        this.to = to;
    }

}
