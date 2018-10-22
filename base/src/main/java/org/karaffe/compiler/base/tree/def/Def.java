package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

import java.util.List;

public interface Def extends Tree {

    void addBody(Tree child);

    List<Tree> getBody();

}
