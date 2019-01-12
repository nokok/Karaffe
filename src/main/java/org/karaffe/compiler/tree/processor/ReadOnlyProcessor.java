package org.karaffe.compiler.tree.processor;

import org.karaffe.compiler.tree.Tree;

public interface ReadOnlyProcessor extends Processor<Void> {

  void readOnly(Tree tree);

  @Override
  default Void ruleBody(Tree tree) {
    readOnly(tree);
    return null;
  }
}
