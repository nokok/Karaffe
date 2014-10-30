package net.nokok.karaffe.javacc.ast;

import java.util.ArrayList;
import java.util.List;

public class VariableIdTypePairs extends Literal<List<VariableIdTypePair>> {

    public VariableIdTypePairs() {
        super(new ArrayList<>());
    }

    public VariableIdTypePairs(List<VariableIdTypePair> value) {
        super(value);
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        return visitor.onVariableIdTypePairs(this);
    }

    public void addPair(VariableIdTypePair pair) {
        value.add(pair);
    }

}
