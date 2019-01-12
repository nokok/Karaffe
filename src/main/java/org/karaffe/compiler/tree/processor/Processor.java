package org.karaffe.compiler.tree.processor;

import org.karaffe.compiler.tree.Tree;

public interface Processor<R> {
  default R apply(Tree tree) {
    if (tree == null) {
      throw new NullPointerException();
    }
    if (!preAssert(tree)) {
      return defaultValue();
    }
    R r = ruleBody(tree);
    if (!postAssert(tree, r)) {
      return defaultValue();
    }
    return r;
  }

  default boolean isApplicableTo(Tree tree) {
    return true;
  }

  default R defaultValue() {
    return null;
  }

  default boolean preAssert(Tree tree) {
    return true;
  }

  R ruleBody(Tree tree);

  default boolean postAssert(Tree tree, R value) {
    return true;
  }
}
