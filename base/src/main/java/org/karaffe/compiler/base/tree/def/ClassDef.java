package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.ArrayList;
import java.util.List;

public class ClassDef extends AbstractDef implements Def {

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("class " + this.getName());
        return String.join("\n", lines);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }
}
