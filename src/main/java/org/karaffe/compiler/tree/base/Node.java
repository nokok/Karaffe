package org.karaffe.compiler.tree.base;

import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.Assign;
import org.karaffe.compiler.tree.Empty;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NamedDef;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.TermNode;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.types.InferResult;

public interface Node extends Cloneable {
    public void accept(KaraffeTreeVisitor visitor);

    public void addChild(Node n);

    public List<? extends Node> getChildren();

    public String getID();

    public NodeType getNodeType();

    public Position getPosition();

    public default boolean hasAnyChild() {
        return !getChildren().isEmpty();
    }

    public default boolean isEmptyNode() {
        return this instanceof Empty;
    }

    public default boolean isName() {
        return this instanceof Name;
    }

    public default boolean isNodeList() {
        return this instanceof NodeList;
    }

    public default boolean isNonEmptyNode() {
        return !this.isEmptyNode();
    }

    public default boolean isTermNode() {
        return this instanceof TermNode;
    }

    public default boolean isNamedDef() {
        return this instanceof NamedDef;
    }

    public default boolean isAssign(){
        return this instanceof Assign;
    }

    public NodeList normalize(NormalizeContext context);

    public void replaceChildren(List<Node> replaced);

    public default NodeList toNodeList() {
        return new NodeList(this);
    }

    public default Optional<InferResult> tryTypeInference(final TypeInferenceContext context) {
        return Optional.empty();
    }

    public String vSource();
}
