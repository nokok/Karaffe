package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.TypeDefMember;
import org.karaffe.compiler.tree.v2.statements.LetFieldDef;
import org.karaffe.compiler.tree.v2.statements.MethodDef;

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
