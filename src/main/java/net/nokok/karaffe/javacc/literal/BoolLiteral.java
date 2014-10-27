package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.stmt.*;

public class BoolLiteral implements Literal<Boolean> {

    protected final Boolean value;

    public BoolLiteral(Boolean bool) {
        this.value = bool;
    }

    public BoolLiteral(String bool) {
        this(Boolean.parseBoolean(bool));
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onBooleanLiteral(this);
    }

    @Override
    public Boolean eval(Void arg) {
        return value;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.BOOL;
    }
}
