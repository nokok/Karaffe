package net.nokok.karaffe.javacc.ast;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {

    private final List<ASTNode> nodes = new ArrayList<>();

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onProgram(this);
    }

    public void addElement(ASTNode node) {
        nodes.add(node);
    }

    public List<ASTNode> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public String nodeIdentifier() {
        return "Program";
    }

    @Override
    public String toString() {
        return "Program";
    }

}
