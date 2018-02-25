package org.karaffe.compiler.tree.v2.statements;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class LetFieldDef extends LetDef implements TypeDefMember {

    public LetFieldDef(Position position, SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(position, fieldName, typeName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, Expression initializer) {
        super(fieldName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(fieldName, typeName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, SimpleName typeName) {
        super(fieldName, typeName);
    }

    public LetFieldDef(Position position, SimpleName fieldName, Expression initializer) {
        super(position, fieldName, initializer);
    }

    public LetFieldDef(Position position, SimpleName fieldName, SimpleName typeName) {
        super(position, fieldName, typeName);
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.LET_FIELD_DEF;
    }

}
