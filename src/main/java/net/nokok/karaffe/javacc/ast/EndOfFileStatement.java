package net.nokok.karaffe.javacc.ast;

public class EndOfFileStatement extends Statement {

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onEndOfFileStatement(this);
    }

    @Override
    public String nodeIdentifier() {
        return "EOF";
    }

    @Override
    public String toString() {
        return "EOF";
    }

}
