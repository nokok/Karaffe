package org.karaffe.compiler.base.tree.expr;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.term.Terms;

public class Apply extends AbstractTree {

    Apply() {
        this(null);
    }

    Apply(Tree parent) {
        super(parent, TreeKind.APPLY);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    public Tree getTarget() {
        return this.getChild(0);
    }

    public Tree getArgs() {
        if (this.getChildren().size() > 1) {
            return this.getChild(1);
        }
        return Terms.emptyTree();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getChild(0));
        sb.append(".");
        sb.append(this.getName());
        if (this.getChildren().size() > 1) {
            Tree child = this.getChild(1);
            TreeKind kind = child.getKind();
            if (kind == TreeKind.TUPLE || kind == TreeKind.EMPTY) {
                sb.append(this.getChild(1));
            } else {
                sb.append("(");
                sb.append(this.getChild(1));
                sb.append(")");
            }
        }
        return sb.toString();
    }
}
