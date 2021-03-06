package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SimpleTree implements Tree {
  private Tree parent;
  private NodeType nodeType;
  private List<Tree> children = new ArrayList<>();

  SimpleTree(NodeType nodeType) {
    this.nodeType = Objects.requireNonNull(nodeType);
  }

  @Override
  public NodeType getNodeType() {
    return this.nodeType;
  }

  @Override
  public List<Tree> getChildren() {
    return this.children;
  }

  @Override
  public boolean hasChildren() {
    return !this.children.isEmpty();
  }

  @Override
  public void replaceThis(Tree after) {
    this.nodeType = after.getNodeType();
    this.children = after.getChildren();
    for (Tree child : children) {
      ((SimpleTree) child).setParent(this);
    }
  }

  @Override
  public void addAllChildren(List<Tree> children) {
    for (Tree tree : children) {
      this.addChild(tree);
    }
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public Tree getParent() {
    return parent;
  }

  void setParent(Tree tree) {
    this.parent = Objects.requireNonNull(tree);
  }

  @Override
  public void addChild(Tree child) {
    this.children.add(Objects.requireNonNull(child));
    ((SimpleTree) child).setParent(this);
  }

  @Override
  public String toString() {
    return String.format("%s (\"\", %s)", this.getNodeType().name(), this.children);
  }
}
