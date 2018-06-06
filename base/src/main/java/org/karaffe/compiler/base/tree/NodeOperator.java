package org.karaffe.compiler.base.tree;

import java.util.List;

public interface NodeOperator {
    void addChild(Tree child);

    void setOrReplaceChild(int index, Tree child);

    List<Tree> getChildren();

    void setChildren(List<Tree> children);
}
