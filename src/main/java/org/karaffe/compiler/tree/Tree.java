package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class Tree {
    private String nodeId = UUID.randomUUID().toString();
    private Position position;
    private NodeType nodeType;
    private String name;
    private List<Tree> children;

    public Tree(NodeType nodeType, String name, Position position) {
        this.position = Objects.requireNonNull(position);
        this.nodeType = Objects.requireNonNull(nodeType);
        this.name = Objects.requireNonNull(name);
        this.children = new ArrayList<>();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void addChild(Tree child) {
        this.children.add(Objects.requireNonNull(child));
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public String getName() {
        return name;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    public Position getPosition() {
        return position;
    }

    public Optional<Tree> findFirstFromChildren(Predicate<Tree> p) {
        return this.getChildren().stream().filter(p).findFirst();
    }

    public Optional<Tree> findFirstFromChildren(NodeType nodeType) {
        return findFirstFromChildren(p -> p.getNodeType().equals(nodeType));
    }

    public boolean hasChildren(NodeType nodeType) {
        return findFirstFromChildren(p -> p.getNodeType().equals(nodeType)).isPresent();
    }

    public int indexOf(NodeType nodeType) {
        return this.getChildren().indexOf(this.findFirstFromChildren(nodeType).orElse(null));
    }

    public void replaceThis(Tree after) {
        this.nodeType = after.getNodeType();
        this.name = after.getName();
        this.children = after.getChildren();
    }

    @Override
    public String toString() {
        return String.format("%s (\"%s\", %s)", this.nodeType.name(), this.name, this.children);
    }

    public void addAllChildren(List<Tree> children) {
        this.children.addAll(Objects.requireNonNull(children));
    }
}
