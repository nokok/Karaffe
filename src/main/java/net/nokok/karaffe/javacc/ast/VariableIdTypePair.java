package net.nokok.karaffe.javacc.ast;

/**
 * varName : TypeName
 *
 * @author noko
 */
public class VariableIdTypePair extends Statement {

    private final VariableId variableId;
    private final TypeId typeId;

    public VariableIdTypePair(VariableId variableId, TypeId typeId) {
        this.variableId = variableId;
        this.typeId = typeId;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVariableIdTypePair(this);
    }

    @Override
    public String nodeIdentifier() {
        return "VariableIdTypePair";
    }
}
