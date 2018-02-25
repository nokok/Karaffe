package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.imports.SimpleImport;
import org.karaffe.compiler.tree.v2.statements.ClassDef;
import org.karaffe.compiler.tree.v2.statements.InterfaceDef;
import org.karaffe.compiler.tree.v2.statements.LetFieldDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

public interface StatementTransformer extends LetLocalDefTransformer {

    @Override
    public default Statement transform(Statement statement) {
        switch (statement.getStatementType()) {
        case EXPRESSION:
            return transform((Expression) statement);
        case CLASS_DEF:
            return transform((ClassDef) statement);
        case INTERFACE_DEF:
            return transform((InterfaceDef) statement);
        case LET_FIELD_DEF:
            return transform((LetFieldDef) statement);
        case LOCAL_LET_DEF:
            return transform((LetLocalDef) statement);
        case METHOD_DEF:
            return transform((MethodDef) statement);
        case SIMPLE_IMPORT_DEF:
            return transform((SimpleImport) statement);
        default:
            throw new UnsupportedOperationException(statement.getStatementType().name());
        }
    }

}
