package org.karaffe.compiler.tree.formatter;

import org.karaffe.compiler.util.CompilerContext;

public interface InternalStateFormatter {
  static InternalStateFormatter fromType(FormatType type) {
    if (type == FormatType.SIMPLE) {
      return new SimpleTreeFormatter();
    } else if (type == FormatType.SOURCE) {
      return new ConcreteSyntaxTreeFormatter();
    } else {
      throw new IllegalArgumentException(type.name());
    }
  }

  String format(CompilerContext context);
}
