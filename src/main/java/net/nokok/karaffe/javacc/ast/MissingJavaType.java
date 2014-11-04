package net.nokok.karaffe.javacc.ast;

public class MissingJavaType extends Literal<Exception> implements ErrorNode {

    private final String missingFQCN;
    private final Exception cause;

    public MissingJavaType(String missingFQCN, Exception cause) {
        super(cause);
        this.missingFQCN = missingFQCN;
        this.cause = cause;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onMissingJavaType(this);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.MISSING_JAVA_TYPE;
    }

    public String missingFQCN() {
        return missingFQCN;
    }

    @Override
    public String nodeIdentifier() {
        return "MissingJavaTypeLiteral";
    }
}
