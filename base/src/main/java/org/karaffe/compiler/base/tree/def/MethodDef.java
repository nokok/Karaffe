package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.stream.Collectors;

public class MethodDef extends AbstractDef implements Def {

    public Tree getParameters() {
        return this.getChild(0);
    }

    public Tree getMethodBody() {
        return this.getChild(1);
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

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
