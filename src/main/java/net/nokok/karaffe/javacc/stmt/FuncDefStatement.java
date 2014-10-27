package net.nokok.karaffe.javacc.stmt;

import net.nokok.karaffe.javacc.Name;
import net.nokok.karaffe.javacc.ast.CodeBlock;

public class FuncDefStatement implements Statement {

    protected final Name name;
    protected final FunctionType type;
    protected final CodeBlock body;

    public FuncDefStatement(Name name, FunctionType type, CodeBlock body) {
        this.name = name;
        this.type = type;
        this.body = body;
    }

    @Override
    public Object accept(StatementListener listener) {
        return listener.onFuncDefStatement(this);
    }

    @Override
    public StatementType getType() {
        return StatementType.FUNCTION_DECLARATION;
    }

    @Override
    public boolean isAllowingSideEffectModifier() {
        return true;
    }

}
