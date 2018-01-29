package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.TypeDef.ClassDef;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

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

		public Node findNameNode() {
			return this.getChildren().get(0);
		}

		public Node findSuperClassNameNode() {
			return this.getChildren().get(1);
		}

		public Node findClassBodyNode() {
			return this.getChildren().get(2);
		}

		@Override
		public String vSource() {
			return String.format("class %s extends %s %s", findNameNode().vSource(), findSuperClassNameNode().vSource(),
					findClassBodyNode().vSource());
		}

		@Override
		public NodeList normalize(NormalizeContext context) {
			Node nameNode = this.findNameNode();
			Node superClassNode = this.findSuperClassNameNode();
			Node normalizedBlock = this.findClassBodyNode().normalize(context);
			return new ClassDef(nameNode, superClassNode, normalizedBlock).toNodeList();
		}

	}

}
