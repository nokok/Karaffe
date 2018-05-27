package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

public interface Def extends Tree {
    void addBody(Tree child);
}
