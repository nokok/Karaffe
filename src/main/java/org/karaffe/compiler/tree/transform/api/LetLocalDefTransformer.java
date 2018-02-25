package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.statements.LetLocalDef;

public interface LetLocalDefTransformer extends BaseTransformer, SimpleNameTransformer, TypeNameTransformer {

    public default void onLetLocalDefBefore(LetLocalDef letLocalDef) {

    }

    public default void onLetLocalDefAfter(LetLocalDef letLocalDef) {

    }

    public default LetLocalDef transform(LetLocalDef oldLocalLetDef) {
        onLetLocalDefBefore(oldLocalLetDef);
        LetLocalDef letLocalDef = new LetLocalDef(
                oldLocalLetDef.getPosition(),
                transform(oldLocalLetDef.getName()),
                oldLocalLetDef.getTypeName().map(this::transform).orElse(null),
                oldLocalLetDef.getInitializer().map(this::transform).orElse(null));
        onLetLocalDefAfter(letLocalDef);
        return letLocalDef;
    }
}
