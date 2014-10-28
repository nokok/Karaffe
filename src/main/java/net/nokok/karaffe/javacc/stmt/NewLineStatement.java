package net.nokok.karaffe.javacc.stmt;

public class NewLineStatement implements Statement {

    @Override
    public Object accept(StatementListener listener) {
        return listener.onNewLineStatement(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.SCOPE_SPLITTER;
    }

}
