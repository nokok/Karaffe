package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.term.NameKind;

public class LetDef extends AbstractDef implements Def {
    public LetDef() {
        this(null);
    }

    public LetDef(Tree parent) {
        super(parent, DefKind.LET);
    }

    public Tree getInitializer() {
        return this.getChild(0);
    }

    @Override
    public String toString() {
        StringBuilder letDef = new StringBuilder();
        letDef.append("let ");
        letDef.append(this.getName());
        if (this.getTypeName().getNameKind() != NameKind.NONE) {
            letDef.append(" ").append(this.getTypeName());
        }
        if (this.getInitializer().getKind() != TreeKind.EMPTY) {
            letDef.append(" = ").append(this.getInitializer());
        }
        return letDef.toString();
    }
}
