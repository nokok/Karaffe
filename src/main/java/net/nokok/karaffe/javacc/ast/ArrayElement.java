package net.nokok.karaffe.javacc.ast;

import java.nio.charset.Charset;

public class ArrayElement extends Literal<ASTNode> {

    public ArrayElement(ASTNode value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onArrayElement(this);
    }

    public String elementTypeHash() {
        return "AryElmentType_" + value.nodeIdentifier().getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public String nodeIdentifier() {
        return "ArrayElement";
    }

    public ASTNode getValue() {
        return value;
    }

}
