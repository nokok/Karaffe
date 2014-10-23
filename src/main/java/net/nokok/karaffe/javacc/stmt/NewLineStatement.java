package net.nokok.karaffe.javacc.stmt;

public class NewLineStatement implements Statement {

    @Override
    public StatementType getType() {
        return StatementType.NEWLINE;
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return false;
    }

}
