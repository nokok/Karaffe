package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.statements.LetFieldDef;

public interface LetFieldDefTransformer extends BaseTransformer, TypeNameTransformer {

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
