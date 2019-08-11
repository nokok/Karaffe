package org.karaffe.compiler.tree.formatter;

import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.KaraffeSource;

public class ConcreteSyntaxTreeFormatter implements InternalStateFormatter {
  @Override
  public String format(CompilerContext context) {
    return context.getSourceFiles().stream().map(KaraffeSource::getConcreteSyntaxTree).reduce((l, r) -> l + "\n" + r).orElse("");
  }
}
