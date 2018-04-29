package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefMember;
import org.karaffe.compiler.frontend.karaffe.ast.statements.LetFieldDef;
import org.karaffe.compiler.frontend.karaffe.ast.statements.MethodDef;

public interface TypeDefMemberTransformer extends LetFieldDefTransformer, MethodDefTransformer, StatementTransformer {
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
