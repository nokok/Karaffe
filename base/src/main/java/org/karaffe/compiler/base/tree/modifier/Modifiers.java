package org.karaffe.compiler.base.tree.modifier;

import org.karaffe.compiler.base.pos.Position;

public interface Modifiers {

    static Modifier modPublic(Position pos) {
        PublicModifier m = new PublicModifier();
        m.setPos(pos);
        return m;
    }

    static Modifier modStatic(Position pos) {
        PublicModifier m = new PublicModifier();
        m.setPos(pos);
        return m;
    }

    static Modifier modSynthetic(Position pos) {
        PublicModifier m = new PublicModifier();
        m.setPos(pos);
        return m;
    }
}
