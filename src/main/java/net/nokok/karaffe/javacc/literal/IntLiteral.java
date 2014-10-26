package net.nokok.karaffe.javacc.literal;

import net.nokok.karaffe.runtime.type.Int;

public class IntLiteral implements Literal<Int> {

    private final Integer value;

    public IntLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public Int eval(Void arg) {
        return new Int(value);
    }

    @Override
    public LiteralType getType() {
        return LiteralType.INT;
    }

}
