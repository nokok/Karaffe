package net.nokok.karaffe.javacc.ast;

import java.util.Optional;

public class FunctionLiteral extends Literal<Expression<?, ?>> {

    private final Optional<VariableIdTypePairs> varIdPairs;
    private final Optional<VariableList> varList;

    public FunctionLiteral(Expression<?, ?> value) {
        super(value);
        varIdPairs = null;
        varList = null;
    }

    FunctionLiteral(VariableIdTypePairs varIdPairs, Expression<?, ?> expr) {
        super(expr);
        this.varIdPairs = Optional.of(varIdPairs);
        this.varList = Optional.empty();
    }

    FunctionLiteral(VariableList varList, Expression<?, ?> expr) {
        super(expr);
        this.varList = Optional.of(varList);
        this.varIdPairs = Optional.empty();
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFunctionLiteral(this);
    }

    @Override
    public String nodeIdentifier() {
        return "FunctionLiteral";
    }

}
