package net.nokok.karaffe.javacc.ast;

public class JavaTypeLiteral extends Literal<Class<?>> {

    public JavaTypeLiteral(Class<?> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onJavaTypeLiteral(this);
    }

}
