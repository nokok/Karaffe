package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.identifier.TypeId;

public class MutableTypeAliasStatement implements Statement {

    private final TypeAliasStatement statement;

    public MutableTypeAliasStatement(TypeAliasStatement s) {
        this.statement = s;
    }

    public MutableTypeAliasStatement(TypeId baseType, TypeId newType) {
        this(new TypeAliasStatement(baseType, newType));
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onMutableTypeAliasStatement(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.S_TYPE_ALIAS;
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return true;
    }

}
