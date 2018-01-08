package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public abstract class TypeDef extends AbstractNode {

    public TypeDef(final NodeType type, final List<Node> children) {
        super(type, children);
    }

    public String getClassName() {
        return ((Name) this.getChildren().get(0)).getText();
    }

    public static class ClassDef extends TypeDef {
        public ClassDef(final Node className, final Node superClassName, final Node block) {
            super(NodeType.DEFCLASS, new ArrayList<>(Arrays.asList(className, superClassName, block)));
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }

}
