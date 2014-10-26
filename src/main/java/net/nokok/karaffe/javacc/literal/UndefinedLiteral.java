package net.nokok.karaffe.javacc.literal;

public class UndefinedLiteral implements Literal<Void> {

    @Override
    public Void eval(Void arg) {
        return null;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.UNDEFINED;
    }

}
