package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;

public abstract class TypeDef extends AbstractNode {

    public TypeDef(final NodeType type, final List<? extends Node> children) {
        super(type, children);
    }

    public String getClassName() {
        return ((VarName) this.getChildren().get(0)).getText();
    }

    public static class ClassDef extends TypeDef {
        public ClassDef(final VarName className, final VarName superClassName, final Block block) {
            super(NodeType.DEFCLASS, new ArrayList<>(Arrays.asList(className, superClassName, block)));
        }
    }

}
