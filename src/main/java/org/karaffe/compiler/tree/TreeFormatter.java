package org.karaffe.compiler.tree;

import org.karaffe.compiler.util.CompilerContext;

import java.util.Objects;
import java.util.stream.Collectors;

public class TreeFormatter {
    private final CompilerContext context;
    private boolean outputId = false;

    public TreeFormatter() {
        this.context = null;
    }

    public TreeFormatter(CompilerContext context) {
        this.context = Objects.requireNonNull(context);
    }

    public String format(Tree tree) {
        return format("", tree);
    }

    private String format(String indentString, Tree tree) {
        StringBuilder stringBuilder = new StringBuilder();
        if (outputId) {
            stringBuilder.append(tree.getNodeId()).append(" ");
        }
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

    public String formatWithId(Tree tree) {
        this.outputId = true;
        String format = this.format(tree);
        this.outputId = false;
        return format;
    }
}
