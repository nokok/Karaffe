package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.expressions.IntLiteral;

public interface IntLiteralTransformer {

    public default void onIntLiteralBefore(IntLiteral intLiteral) {

    }

    public default void onIntLiteralAfter(IntLiteral intLiteral) {

    }

    default Expression transform(IntLiteral intLiteral) {
        onIntLiteralBefore(intLiteral);
        IntLiteral after = transformBody(intLiteral);
        onIntLiteralAfter(after);
        return intLiteral;
    }

    public default IntLiteral transformBody(IntLiteral intLiteral) {
        return intLiteral;
    }

}