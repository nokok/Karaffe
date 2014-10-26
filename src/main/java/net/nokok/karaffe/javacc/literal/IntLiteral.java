package net.nokok.karaffe.javacc.literal;

public class IntLiteral implements Literal<Integer> {

    private final Integer value;

    public IntLiteral(Integer value) {
        this.value = value;
    }

    @Override
    public Integer eval(Void arg) {
        return value;
    }

    @Override
    public LiteralType getType() {
        return LiteralType.INT;
    }

}
