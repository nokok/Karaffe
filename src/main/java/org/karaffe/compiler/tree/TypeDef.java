package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public abstract class TypeDef extends AbstractNode {

    public static class ClassDef extends TypeDef {
        public ClassDef(final Node className, final Node superClassName, final Node block) {
            super(NodeType.DEFCLASS, new ArrayList<>(Arrays.asList(className, superClassName, block)));
        }

        @Override
        public void accept(final KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }

        public Node findClassBodyNode() {
            return this.getChildren().get(2);
        }

        public Node findNameNode() {
            return this.getChildren().get(0);
        }

        public Node findSuperClassNameNode() {
            return this.getChildren().get(1);
        }

        @Override
        public NodeList normalize(final NormalizeContext context) {
            final Node nameNode = this.findNameNode();
            final Node superClassNode = this.findSuperClassNameNode();
            final Node normalizedBlock = this.findClassBodyNode().normalize(context);
            return new ClassDef(nameNode, superClassNode, normalizedBlock).toNodeList();
        }

        @Override
        public String vSource() {
            return String.format("class %s extends %s %s", this.findNameNode().vSource(), this.findSuperClassNameNode().vSource(),
                    this.findClassBodyNode().vSource());
        }

    }

    public TypeDef(final NodeType type, final List<Node> children) {
        super(type, children);
    }

    public String getClassName() {
        return ((Name) this.getChildren().get(0)).getText();
    }

}
