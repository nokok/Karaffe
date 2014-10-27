package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.Token;
import net.nokok.karaffe.javacc.stmt.*;

public class FloatLiteral implements Literal<Double> {

    private final Double value;

    public FloatLiteral(Double value) {
        this.value = value;
    }

    public FloatLiteral(String value) {
        this(Double.parseDouble(value));
    }

    public FloatLiteral(Token value) {
        this(Double.parseDouble(value.image));
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onFloatLiteral(this);
    }

    @Override
    public Double eval(Void arg) {
        return value;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.FLOAT;
    }

}
