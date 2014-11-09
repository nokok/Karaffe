package net.nokok.karaffe.javacc.ast;

public class FixedSizeArrayLiteral extends Literal<ArrayElements> {

    private final Class<?> arrayType;

    public FixedSizeArrayLiteral(ArrayElements value, Class<?> arrayType) {
        super(value);
        this.arrayType = arrayType;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onFixedSizeArrayLiteral(this);
    }

    public ArrayElements getElements() {
        return value;
    }

    @Override
    public String nodeIdentifier() {
        return "FixedSizeArrayLiteral";
    }

    public ArrayElements getValue() {
        return value;
    }
}
