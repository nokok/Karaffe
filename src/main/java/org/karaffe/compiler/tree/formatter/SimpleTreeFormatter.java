package org.karaffe.compiler.tree.formatter;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;

import java.util.stream.Collectors;

public class SimpleTreeFormatter implements InternalStateFormatter {
  @Override
  public String format(CompilerContext context) {
    return format("", context.getUntypedTree());
  }

  private String format(String indentString, Tree tree) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(indentString);
    stringBuilder.append(tree.getNodeType().name());
    if (!tree.getName().isEmpty()) {
      stringBuilder.append(" ").append(tree.getName());
    }
    if (tree.hasChildren()) {
      stringBuilder.append("\n");
      stringBuilder.append(
        tree.getChildren()
          .stream()
          .map(child -> format(indentString + "  ", child))
          .collect(Collectors.joining("\n")));
    }
    return stringBuilder.toString();
  }
}
