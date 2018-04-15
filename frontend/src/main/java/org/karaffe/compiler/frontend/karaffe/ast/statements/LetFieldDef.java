package org.karaffe.compiler.frontend.karaffe.ast.statements;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefMember;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class LetFieldDef extends LetDef implements TypeDefMember {

    public LetFieldDef(Position position, SimpleName fieldName, TypeName typeName, Expression initializer) {
        super(position, fieldName, typeName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, Expression initializer) {
        super(fieldName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, TypeName typeName, Expression initializer) {
        super(fieldName, typeName, initializer);
    }

    public LetFieldDef(SimpleName fieldName, TypeName typeName) {
        super(fieldName, typeName);
    }

    public LetFieldDef(Position position, SimpleName fieldName, Expression initializer) {
        super(position, fieldName, initializer);
    }

    public LetFieldDef(Position position, SimpleName fieldName, TypeName typeName) {
        super(position, fieldName, typeName);
    }

    public LetFieldDef(LetFieldDef other) {
        this(other.getPosition(), other.getName(), other.getTypeName().orElse(null), other.getInitializer().orElse(null));
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.LET_FIELD_DEF;
    }

}
