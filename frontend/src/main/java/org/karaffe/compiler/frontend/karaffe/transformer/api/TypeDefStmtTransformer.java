package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.statements.ClassDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.InterfaceDef;

public interface TypeDefStmtTransformer extends ClassDefTransformer, InterfaceDefTransformer {

    @Override
    public default TypeDefStatement transform(TypeDefStatement typeDefStatement) {
        switch (typeDefStatement.getStatementType()) {
        case INTERFACE_DEF:
            return transform((InterfaceDef) typeDefStatement);
        case CLASS_DEF:
            return transform((ClassDef) typeDefStatement);
        default:
            throw new UnsupportedOperationException(typeDefStatement.getStatementType().name());
        }
    }

}
