package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.stmt.*;

public enum UndefinedLiteral implements Literal<UndefinedLiteral> {

    VALUE,;

    @Override
    public Object accept(StatementListener listener) {
        return listener.onUndefinedLiteral(this);
    }

    @Override
    public UndefinedLiteral eval(Void arg) {
        return VALUE;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.UNDEFINED;
    }

}
