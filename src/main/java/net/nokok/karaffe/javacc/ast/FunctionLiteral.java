package net.nokok.karaffe.javacc.ast;

public class FunctionLiteral extends Literal<Expression<?, ?>> {

    private final VariableIdTypePairs varIdPairs;
    private final VariableList varList;

    public FunctionLiteral(Expression<?, ?> value) {
        super(value);
        varIdPairs = null;
        varList = null;
    }

    FunctionLiteral(VariableIdTypePairs varIdPairs, Expression<?, ?> expr) {
        super(expr);
        this.varIdPairs = varIdPairs;
        this.varList = null;
    }

    FunctionLiteral(VariableList varList, Expression<?, ?> expr) {
        super(expr);
        this.varList = varList;
        this.varIdPairs = null;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFunctionLiteral(this);
    }

}
