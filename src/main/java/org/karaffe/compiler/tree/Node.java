package org.karaffe.compiler.tree;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Node extends Positioned {

  Tree getParent();

  void addChild(Tree child);

  NodeType getNodeType();

  List<Tree> getChildren();

  boolean hasChildren();

  default Optional<Tree> findFirstFromChildren(Predicate<Tree> p) {
    return this.getChildren().stream().filter(p).findFirst();
  }

  default Optional<Tree> findFirstFromChildren(NodeType nodeType) {
    return findFirstFromChildren(p -> p.getNodeType().equals(nodeType));
  }

  default List<Tree> findAllFromChildren(Predicate<Tree> p) {
    return this.getChildren().stream().filter(p).collect(Collectors.toList());
  }

  default List<Tree> findAllFromChildren(NodeType nodeType) {
    return findAllFromChildren(p -> p.getNodeType().equals(nodeType));
  }

  default boolean hasChildren(NodeType nodeType) {
    return findFirstFromChildren(p -> p.getNodeType().equals(nodeType)).isPresent();
  }

  default int indexOf(NodeType nodeType) {
    return this.getChildren().indexOf(this.findFirstFromChildren(nodeType).orElse(null));
  }

  void replaceThis(Tree after);

  void addAllChildren(List<Tree> children);
}
