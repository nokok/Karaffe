package org.karaffe.compiler.ast.statements;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.StatementType;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

public class LetLocalDef extends LetDef {

    public LetLocalDef(Position position, SimpleName letName, TypeName typeName, Expression initializer) {
        super(position, letName, typeName, initializer);
    }

    public LetLocalDef(SimpleName letName, Expression initializer) {
        super(letName, initializer);
    }

    public LetLocalDef(SimpleName letName, TypeName typeName, Expression initializer) {
        super(letName, typeName, initializer);
    }

    public LetLocalDef(SimpleName letName, TypeName typeName) {
        super(letName, typeName);
    }

    public LetLocalDef(LetLocalDef other) {
        this(other.getPosition(), other.getName(), other.getTypeName().orElse(null), other.getInitializer().orElse(null));
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.LOCAL_LET_DEF;
    }

}
