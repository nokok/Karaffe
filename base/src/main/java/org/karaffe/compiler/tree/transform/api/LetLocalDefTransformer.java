package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.statements.LetDef;
import org.karaffe.compiler.tree.v2.statements.LetLocalDef;

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
