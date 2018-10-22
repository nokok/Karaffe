package org.karaffe.compiler.base.tree;

import java.util.List;

public interface NodeOperator {
    void addFirst(Tree child);

    void addChild(Tree child);

    void setOrReplaceChild(int index, Tree child);

    List<Tree> getChildren();

    void setChildren(List<Tree> children);

    default Tree getChild(int index) {
        return getChildren().get(index);
    }
}
