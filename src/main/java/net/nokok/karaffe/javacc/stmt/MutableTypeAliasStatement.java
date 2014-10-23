package net.nokok.karaffe.javacc.stmt;

public class MutableTypeAliasStatement implements Statement {

    private final TypeAliasStatement statement;

    public MutableTypeAliasStatement(TypeAliasStatement s) {
        this.statement = s;
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
