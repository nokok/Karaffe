package net.nokok.karaffe.javacc.stmt;

import java.util.Set;
import net.nokok.karaffe.javacc.Name;
import net.nokok.karaffe.javacc.ast.CodeBlock;
import net.nokok.karaffe.javacc.modifier.*;

public class FuncDefStatement implements Statement {

    protected final Set<Modifier> modifiers;
    protected final Name name;
    protected final FunctionType type;
    protected final CodeBlock body;

    public FuncDefStatement(Set<Modifier> modifiers, Name name, FunctionType type, CodeBlock body) {
        this.modifiers = modifiers;
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

}
