package net.nokok.karaffe.javacc.stmt;

import java.util.Set;
import net.nokok.karaffe.javacc.expr.*;
import net.nokok.karaffe.javacc.identifier.TypeId;
import net.nokok.karaffe.javacc.identifier.VariableId;
import net.nokok.karaffe.javacc.modifier.*;

public class VariableDeclaration implements Statement {

    private final Set<Modifier> modifiers;
    private final VariableId name;
    private final TypeId type;
    private final Expression<?, ?> expr;

    public VariableDeclaration(Set<Modifier> modifiers, VariableId name, TypeId type, Expression<?, ?> expr) {
        this.modifiers = modifiers;
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
