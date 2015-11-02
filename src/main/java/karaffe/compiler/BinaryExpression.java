package karaffe.compiler;

public interface BinaryExpression {

    public Class<?> leftInferredType();

    public Class<?> rightInferredType();
}
