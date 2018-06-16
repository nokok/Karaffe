package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

import java.util.stream.Collectors;

public class MethodDef extends AbstractDef implements Def {
    public MethodDef() {
        this(null);
    }

    public MethodDef(Tree parent) {
        super(parent, DefKind.METHOD);
    }

    @Override
    public String toString() {
        String mods = String.join(" ", getModifiers().stream().map(Object::toString).collect(Collectors.toList()));
        mods = mods.isEmpty() ? "" : mods + " ";


        return String.format("%s%s%s%s",
                mods,
                getTypeName() + " ",
                getName(),
                getChild(0)
        );
    }
}
