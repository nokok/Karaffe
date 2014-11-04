package net.nokok.karaffe.javacc.ast;

public interface ASTNode {

    public Object accept(ASTVisitor visitor);

    public String nodeIdentifier();
}
