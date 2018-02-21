package org.karaffe.compiler.tree.v2.api;

import java.util.List;

public interface Tree {
    public List<? extends Attribute> getAttributes();

    public void accept(TreeVisitor visitor);

}
