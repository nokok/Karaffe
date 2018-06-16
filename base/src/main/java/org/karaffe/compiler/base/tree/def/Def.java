package org.karaffe.compiler.base.tree.def;

import org.karaffe.compiler.base.tree.Tree;

import java.util.List;

public interface Def extends Tree {

    DefKind getDefKind();

    void setDefKind(DefKind defKind);

    void addBody(Tree child);

    List<Tree> getBody();

}
