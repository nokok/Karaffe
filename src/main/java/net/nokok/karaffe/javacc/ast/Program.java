package net.nokok.karaffe.javacc.ast;

public class Program implements ASTNode {

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onProgram(this);
    }

}
