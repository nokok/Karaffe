package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.javacc.type.UndefinedValue;

public class UndefinedLiteral implements Literal<UndefinedValue> {

    @Override
    public UndefinedValue eval(Void arg) {
        return UndefinedValue.VALUE;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.UNDEFINED;
    }

}
