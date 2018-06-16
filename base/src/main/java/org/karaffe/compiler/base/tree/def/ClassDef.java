package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class ClassDef extends AbstractDef implements Def {
    public ClassDef() {
        this(null);
    }

    public ClassDef(Tree parent) {
        super(parent, DefKind.CLASS);
    }

    @Override
    public String toString() {
        List<String> lines = new ArrayList<>();
        lines.add("class " + this.getName());
        return String.join("\n", lines);
    }
}
