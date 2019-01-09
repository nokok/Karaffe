package org.karaffe.compiler.tree.formatter;

import org.karaffe.compiler.tree.Tree;

public interface TreeFormatter {
  static TreeFormatter fromType(FormatType type) {
    if (type == FormatType.SIMPLE) {
      return new SimpleTreeFormatter();
    } else {
      throw new IllegalArgumentException(type.name());
    }
  }

  public String format(Tree tree);
}
