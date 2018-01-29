package org.karaffe.compiler.tree.base;

import java.util.List;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.TermNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.util.NormalizeContext;

public interface Node extends Cloneable {
	public String getID();

	public NodeType getNodeType();

	public Position getPosition();

	public String vSource();

	public NodeList normalize(NormalizeContext context);

	public default boolean hasAnyChild() {
		return !getChildren().isEmpty();
	}

	public void replaceChildren(List<Node> replaced);

	public List<? extends Node> getChildren();

	public void addChild(Node n);

	public default boolean isTermNode() {
		return this instanceof TermNode;
	}

	public default boolean isEmptyNode() {
		return this instanceof Empty;
	}
	
	public default boolean isNonEmptyNode() {
		return !this.isEmptyNode();
	}
	
	public default boolean isName() {
		return this instanceof Name;
	}

	public default boolean isNodeList() {
		return this instanceof NodeList;
	}

	public default NodeList toNodeList() {
		return new NodeList(this);
	}

	public void accept(KaraffeTreeVisitor visitor);
}
