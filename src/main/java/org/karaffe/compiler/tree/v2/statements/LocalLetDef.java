package org.karaffe.compiler.tree.v2.statements;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class LocalLetDef extends LetDef implements Statement {

    public LocalLetDef(Position position, SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(position, fieldName, typeName, initializer);
    }

    public LocalLetDef(SimpleName fieldName, Expression initializer) {
        super(fieldName, initializer);
    }

    public LocalLetDef(SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(fieldName, typeName, initializer);
    }

    public LocalLetDef(SimpleName fieldName, SimpleName typeName) {
        super(fieldName, typeName);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.LOCAL_LET_DEF;
    }

}
