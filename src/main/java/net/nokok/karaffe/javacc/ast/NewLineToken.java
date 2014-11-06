package net.nokok.karaffe.javacc.ast;

public class NewLineToken extends Statement {

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onNewLineToken(this);
    }

    @Override
    public String nodeIdentifier() {
        return "NewLine";
    }

}
