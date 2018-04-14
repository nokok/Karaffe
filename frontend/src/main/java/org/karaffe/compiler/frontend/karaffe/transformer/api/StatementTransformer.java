package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.imports.SimpleImport;
import org.karaffe.compiler.ast.statements.ClassDef;
import org.karaffe.compiler.ast.statements.InterfaceDef;
import org.karaffe.compiler.ast.statements.LetFieldDef;
import org.karaffe.compiler.ast.statements.LetLocalDef;
import org.karaffe.compiler.ast.statements.MethodDef;

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
