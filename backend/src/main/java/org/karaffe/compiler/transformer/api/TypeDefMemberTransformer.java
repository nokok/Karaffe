package org.karaffe.compiler.transformer.api;

import org.karaffe.compiler.ast.api.TypeDefMember;
import org.karaffe.compiler.ast.statements.LetFieldDef;
import org.karaffe.compiler.ast.statements.MethodDef;

public interface TypeDefMemberTransformer extends LetFieldDefTransformer, MethodDefTransformer {
    @Override
    default TypeDefMember transform(TypeDefMember oldMember) {
        switch (oldMember.getStatementType()) {
        case LET_FIELD_DEF:
            return transform((LetFieldDef) oldMember);
        case METHOD_DEF:
            return transform((MethodDef) oldMember);
        default:
            throw new UnsupportedOperationException(oldMember.getStatementType().name());
        }
    }

}