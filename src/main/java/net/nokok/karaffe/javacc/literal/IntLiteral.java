package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.stmt.*;

public class IntLiteral implements Literal<Integer> {

    private final Integer value;

    public IntLiteral(Integer value) {
        this.value = value;
    }

    public IntLiteral(String value) {
        this(Integer.parseInt(value));
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onIntLiteral(this);
    }

    @Override
    public Integer eval(Void arg) {
        return value;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.INT;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
