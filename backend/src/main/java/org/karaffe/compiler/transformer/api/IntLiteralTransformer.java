package org.karaffe.compiler.transformer.api;

import org.karaffe.compiler.ast.api.Expression;
import org.karaffe.compiler.ast.expressions.IntLiteral;

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