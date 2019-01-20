package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.processor.Processor;
import org.karaffe.compiler.tree.query.TreeQuery;

import java.util.Optional;

public interface Tree extends Term {

  default Optional<Tree> dig(NodeType... simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeChildren(this);
  }

  default Tree forceDig(NodeType... simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeChildren(this).orElseThrow(IllegalStateException::new);
  }

  default Optional<Tree> climb(NodeType simpleQuery) {
    TreeQuery treeQuery = TreeQuery.buildFrom(simpleQuery);
    return treeQuery.executeParent(this);
  }

  default <R> R applyProcessor(Processor<R> processor) {
    return processor.apply(this);
  }
}
