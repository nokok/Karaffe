package net.nokok.karaffe.javacc.ast;

import java.util.function.Function;

public class FunctionLiteral extends Literal<Function<?, ?>> {

    public FunctionLiteral(Function<?, ?> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFunctionLiteral(this);
    }

}
