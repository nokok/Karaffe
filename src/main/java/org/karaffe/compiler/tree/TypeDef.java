package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;

public abstract class TypeDef extends AbstractNode {

    private final Name className;

    public TypeDef(final Name className) {
        super(NodeType.DEFCLASS, className.getPosition());
        this.className = className;
    }

    public String getClassName() {
        return this.className.getText();
    }

}
