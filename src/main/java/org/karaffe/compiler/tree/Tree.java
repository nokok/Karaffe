package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.processor.Processor;
import org.karaffe.compiler.tree.query.TreeQuery;
import org.karaffe.compiler.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Tree {
  private Position position;
  private Tree parent;
  private NodeType nodeType;
  private String name;
  private List<Tree> children;

  public Tree(NodeType nodeType) {
    this(nodeType, "", Position.noPos());
  }

  public Tree(NodeType nodeType, String name) {
    this(nodeType, name, Position.noPos());
  }

  public Tree(NodeType nodeType, Position position) {
    this(nodeType, "", position);
  }

  public Tree(NodeType nodeType, String name, Position position) {
    this.position = Objects.requireNonNull(position);
    this.nodeType = Objects.requireNonNull(nodeType);
    this.name = Objects.requireNonNull(name);
    this.children = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public Tree getParent() {
    return parent;
  }

  private void setParent(Tree parent) {
    this.parent = parent;
  }

  public void addChild(Tree child) {
    this.children.add(Objects.requireNonNull(child));
    child.setParent(this);
  }

  public NodeType getNodeType() {
    return nodeType;
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

  public Optional<Tree> dig(NodeType... simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeChildren(this);
  }

  public Tree forceDig(NodeType... simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeChildren(this).orElseThrow(IllegalStateException::new);
  }

  public Optional<Tree> climb(NodeType simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeParent(this);
  }

  public boolean hasChildren(NodeType nodeType) {
    return findFirstFromChildren(p -> p.getNodeType().equals(nodeType)).isPresent();
  }

  public int indexOf(NodeType nodeType) {
    return this.getChildren().indexOf(this.findFirstFromChildren(nodeType).orElse(null));
  }

  public void replaceThis(Tree after) {
    this.nodeType = after.getNodeType();
    this.children = after.getChildren();
    if (this.position.isNoPos() && !after.position.isNoPos()) {
      this.position = after.getPosition();
    }
  }

  public <R> R applyProcessor(Processor<R> processor) {
    return processor.apply(this);
  }

  @Override
  public String toString() {
    return String.format("%s (\"%s\", %s)", this.nodeType.name(), this.name, this.children);
  }

  public void addAllChildren(List<Tree> children) {
    this.children.addAll(Objects.requireNonNull(children));
  }
}
