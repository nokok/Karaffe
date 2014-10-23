package net.nokok.karaffe.javacc.stmt;

public class MutableTypeAliasStatement implements Statement {

    private final TypeAliasStatement statement;

    public MutableTypeAliasStatement(TypeAliasStatement s) {
        this.statement = s;
    }

    @Override
    public StatementType getType() {
        return statement.getType();
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return true;
    }

}
