package net.nokok.karaffe.javacc.ast;

public class VariableDeclaration extends Statement {

    private final VariableId name;
    private final TypeId type;
    private final ASTNode node;

    public VariableDeclaration(VariableId name, TypeId type, ASTNode node) {
        this.name = name;
        this.type = type;
        this.node = node;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVariableDeclaration(this);
    }

    @Override
    public String nodeIdentifier() {
        return "VariableDeclaration";
    }

    public String getNameString() {
        return name.getName();
    }

    public String getTypeString() {
        return type.getName();
    }

    public ASTNode getNode() {
        return node;
    }

}
