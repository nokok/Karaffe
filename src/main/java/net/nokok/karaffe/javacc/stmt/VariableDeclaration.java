package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.expr.*;
import net.nokok.karaffe.javacc.identifier.TypeId;
import net.nokok.karaffe.javacc.identifier.VariableId;

public class VariableDeclaration implements Statement {

    private final VariableId name;
    private final TypeId type;
    private final Expression<?, ?> expr;

    public VariableDeclaration(VariableId name, TypeId type, Expression<?, ?> expr) {
        this.name = name;
        this.type = type;
        this.expr = expr;
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onVariableDeclaration(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.VARIABLE_DECLARATION;
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return false;
    }

    public String variableName() {
        return name.toString();
    }

    public TypeId getTypeId() {
        return type;
    }

    public boolean isNeedTypeInference() {
        return type.equals(TypeId.Inference);
    }

    public Object evalExpr() {
        return expr.eval(null);
    }

}
