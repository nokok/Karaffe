package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.stmt.*;
import net.nokok.karaffe.javacc.type.UndefinedValue;

public class UndefinedLiteral implements Literal<UndefinedValue> {

    @Override
    public Object accept(StatementListener listener) {
        return listener.onUndefinedLiteral(this);
    }

    @Override
    public UndefinedValue eval(Void arg) {
        return UndefinedValue.VALUE;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.UNDEFINED;
    }

}
