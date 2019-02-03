package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.Position;

import java.util.Arrays;

public final class TreeFactory {
  public static Tree newTree(NodeType nodeType) {
    return new SimpleTree(nodeType);
  }

  public static Tree newTree(NodeType nodeType, String name) {
    return new NamedTree(nodeType, name);
  }

  public static Tree newTree(NodeType nodeType, Position position) {
    return new PositionedSimpleTree(nodeType, position);
  }

  public static Tree newTree(NodeType nodeType, String name, Position position) {
    return new NamedPositionTree(nodeType, name, position);
  }

  public static Tree newTree(NodeType nodeType, Tree... children) {
    Tree tree = TreeFactory.newTree(nodeType);
    tree.addAllChildren(Arrays.asList(children));
    return tree;
  }

  public static Tree newTree(NodeType nodeType, String name, Tree... children) {
    Tree tree = TreeFactory.newTree(nodeType, name);
    tree.addAllChildren(Arrays.asList(children));
    return tree;
  }

  public static Tree newTree(NodeType nodeType, Position position, Tree... children) {
    Tree tree = TreeFactory.newTree(nodeType, position);
    tree.addAllChildren(Arrays.asList(children));
    return tree;
  }

  public static Tree newTree(NodeType nodeType, String name, Position position, Tree... children) {
    Tree tree = TreeFactory.newTree(nodeType, name, position);
    tree.addAllChildren(Arrays.asList(children));
    return tree;
  }
}
