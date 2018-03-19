package org.karaffe.compiler.backend.transformer.api;

import org.karaffe.compiler.ast.statements.LetDef;
import org.karaffe.compiler.ast.statements.LetLocalDef;

public interface LetLocalDefTransformer extends BaseTransformer, TypeNameTransformer {

    public default void onLetLocalDefBefore(LetLocalDef letLocalDef) {

    }

    public default void onLetLocalDefAfter(LetDef letLocalDef) {

    }

    public default LetLocalDef transformBody(LetLocalDef oldLocalLetDef) {
        return new LetLocalDef(
                oldLocalLetDef.getPosition(),
                transform(oldLocalLetDef.getName()),
                oldLocalLetDef.getTypeName().map(this::transform).orElse(null),
                oldLocalLetDef.getInitializer().map(this::transform).orElse(null));
    }

    public default LetLocalDef transform(LetLocalDef oldLocalLetDef) {
        onLetLocalDefBefore(oldLocalLetDef);
        LetLocalDef letLocalDef = transformBody(oldLocalLetDef);
        onLetLocalDefAfter(letLocalDef);
        return letLocalDef;
    }
}
