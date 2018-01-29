package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public class VarDef extends AbstractNode {

	public VarDef(final Node modifiers, final Node name, final Node type) {
		super(NodeType.DEFVAR, new ArrayList<>(Arrays.asList(modifiers, name, type)));
	}

	public VarDef(final Node modifiers, final Node name, final Node type, Node initializer) {
		super(NodeType.DEFVAR, new ArrayList<>(Arrays.asList(modifiers, name, type, initializer)));
	}

	public boolean has(final Class<? extends ModifierToken> modifier) {
		return ((Modifiers) this.getChildren().get(0)).stream().filter(t -> t.getClass().equals(modifier)).count() != 0;
	}

	public Node findModifierNode() {
		return getChildren().get(0);
	}

	public Node findNameNode() {
		return getChildren().get(1);
	}

	public Node findTypeNameNode() {
		return getChildren().get(2);
	}

	public Optional<Node> findInitializerExprNode() {
		if (getChildren().size() == 3) {
			return Optional.empty();
		}
		return Optional.of(getChildren().get(3));
	}

	public String getName() {
		return ((Name) this.getChildren().get(1)).getText();
	}

	public String getTypeName() {
		return ((TypeName) this.getChildren().get(2)).getText();
	}

	@Override
	public void accept(KaraffeTreeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String vSource() {
		return String.format("%s %s%s;", findTypeNameNode().vSource(), findNameNode().vSource(),
				findInitializerExprNode().map(Node::vSource).orElse(""));
	}

	@Override
	public NodeList normalize(NormalizeContext context) {
		return this.toNodeList();
	}
}
