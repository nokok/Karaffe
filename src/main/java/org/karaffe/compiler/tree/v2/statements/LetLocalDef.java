package org.karaffe.compiler.tree.v2.statements;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class LetLocalDef extends LetDef {

    public LetLocalDef(Position position, SimpleName letName, SimpleName typeName, Expression initializer) {
        super(position, letName, typeName, initializer);
    }

    public LetLocalDef(SimpleName letName, Expression initializer) {
        super(letName, initializer);
    }

    public LetLocalDef(SimpleName letName, SimpleName typeName, Expression initializer) {
        super(letName, typeName, initializer);
    }

    public LetLocalDef(SimpleName letName, SimpleName typeName) {
        super(letName, typeName);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.LOCAL_LET_DEF;
    }
}
