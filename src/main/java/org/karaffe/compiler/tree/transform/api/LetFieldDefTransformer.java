package org.karaffe.compiler.tree.transform.api;

import org.karaffe.compiler.tree.v2.statements.LetFieldDef;

public interface LetFieldDefTransformer extends BaseTransformer, SimpleNameTransformer, TypeNameTransformer {

    public default void onLetFieldDefBefore(LetFieldDef letFieldDef) {

    }

    public default void onLetFieldDefAfter(LetFieldDef letFieldDef) {

    }

    public default LetFieldDef transformBody(LetFieldDef oldLetFieldDef) {
        return new LetFieldDef(
                oldLetFieldDef.getPosition(),
                transform(oldLetFieldDef.getName()),
                oldLetFieldDef.getTypeName().map(this::transform).orElse(null),
                oldLetFieldDef.getInitializer().map(this::transform).orElse(null));
    }

    public default LetFieldDef transform(LetFieldDef oldLetFieldDef) {
        onLetFieldDefBefore(oldLetFieldDef);
        LetFieldDef letFieldDef = transformBody(oldLetFieldDef);
        onLetFieldDefAfter(letFieldDef);
        return letFieldDef;
    }

}
