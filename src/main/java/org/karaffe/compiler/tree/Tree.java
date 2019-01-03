package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Tree {
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

    public boolean hasChildren(NodeType nodeType) {
        return findFirstFromChildren(p -> p.getNodeType().equals(nodeType)).isPresent();
    }

    public int indexOf(NodeType nodeType) {
        return this.getChildren().indexOf(this.findFirstFromChildren(p -> p.getNodeType().equals(nodeType)).orElse(null));
    }

    @Override
    public String toString() {
        return String.format("%s (\"%s\", %s)", this.nodeType.name(), this.name, this.children);
    }
}
