package org.karaffe.compiler.frontend.karaffe.visitor;

import org.karaffe.compiler.base.tree.DefaultVisitor;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.term.NameNode;

public class NormalizeVisitor extends DefaultVisitor<Void> {

    @Override
    public Tree visitNameNode(NameNode tree, Void aVoid) {
        return tree;
    }
}
