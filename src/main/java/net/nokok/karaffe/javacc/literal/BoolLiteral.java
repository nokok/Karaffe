package net.nokok.karaffe.javacc.literal;

public class BoolLiteral implements Literal<Boolean> {

    protected final Boolean value;

    public BoolLiteral(Boolean bool) {
        this.value = bool;
    }

    public BoolLiteral(String bool) {
        this(Boolean.parseBoolean(bool));
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
