package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.expr.*;
import net.nokok.karaffe.javacc.identifier.TypeId;
import net.nokok.karaffe.javacc.identifier.VariableId;

public class MutableVariableDeclaration implements Statement {

    private final VariableDeclaration variableDeclaration;

    public MutableVariableDeclaration(VariableId name, TypeId type, Expression<?, ?> expr) {
        variableDeclaration = new VariableDeclaration(name, type, expr);
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onMutableVariableDeclaration(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.S_VARIABLE_DECLARATION;
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return true;
    }

}
