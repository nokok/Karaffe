package org.karaffe.compiler.frontend.karaffe.subject;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeVisitor;

public interface ToVisitor<P> {
    TreeVisitor<Tree, P> asVisitor();
}
