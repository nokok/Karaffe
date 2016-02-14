package karaffe.compiler;

public interface BinaryExpression {

    public Expression leftExpr();

    public Class<?> leftInferredType();

    public Expression rightExpr();

    public Class<?> rightInferredType();
}
