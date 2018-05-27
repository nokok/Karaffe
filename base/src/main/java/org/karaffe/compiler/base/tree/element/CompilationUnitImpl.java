package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.AbstractTree;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;

public class CompilationUnitImpl extends AbstractTree implements CompilationUnit {

    CompilationUnitImpl() {
        super(null, TreeKind.COMPILE_UNIT);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visitCompileUnit(this, p);
    }

    @Override
    public void addTopLevel(Tree source) {
        this.addChild(source);
    }
}
