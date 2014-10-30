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

    public boolean isValidArrayType() {
        if ( value.getArrayLength() == 0 ) {
            return true;
        }
        Class<?> headElementType = value.getHeadElementType();
        for ( ArrayElement obj : value ) {

        }
        return true;
    }
}
