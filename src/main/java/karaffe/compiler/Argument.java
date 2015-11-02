package karaffe.compiler;

import java.util.Optional;
import org.objectweb.asm.tree.InsnList;

class Argument implements NodeGeneratable<InsnList> {

    private final Expression e;

    Argument(Expression e) {
        this.e = e;
    }

    @Override
    public InsnList toNode() {
        return e.toNode();
    }

    public Class<?> inferredType() {
        return e.inferredType();
    }

    public Optional<Class<?>> leftInferredType() {
        if ( e instanceof BinaryExpression ) {
            BinaryExpression b = (BinaryExpression) e;
            return Optional.of(b.leftInferredType());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Class<?>> rightInferredType() {
        if ( e instanceof BinaryExpression ) {
            BinaryExpression b = (BinaryExpression) e;
            return Optional.of(b.rightInferredType());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "(arg " + e.toString() + ")";
    }

}
