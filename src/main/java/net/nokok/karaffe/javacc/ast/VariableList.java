package net.nokok.karaffe.javacc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VariableList extends Literal<List<VariableId>> implements Iterable<VariableId> {

    public VariableList() {
        super(new ArrayList<>());
    }

    public VariableList(List<VariableId> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVariableList(this);
    }

    @Override
    public Iterator<VariableId> iterator() {
        return value.iterator();
    }

    public void addVariableElement(VariableId variableId) {
        value.add(variableId);
    }

    @Override
    public String nodeIdentifier() {
        return "VariableList";
    }

}
