package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;

public interface IntLiteralTransformer {

    public default void onIntLiteral(IntLiteral intLiteral) {

    }

    default Expression transform(IntLiteral intLiteral) {
        onIntLiteral(intLiteral);
        return new IntLiteral(intLiteral.getRawValue());
    }

}