package org.karaffe.compiler.tree.formatter;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import java.util.stream.Collectors;

public class FlatTreeFormatter implements TreeFormatter {
    @Override
    public String format(Tree tree) {
        return format("", tree);
    }

    private String format(String indentString, Tree tree) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (tree.getNodeType()) {
            case CompilationUnit:
                stringBuilder.append(compilationUnitFormat(indentString, tree));
                break;
            case SourceFile:
                stringBuilder.append(sourceFileFormat(indentString, tree));
                break;
            case DefClass:
                stringBuilder.append(classFormat(indentString, tree));
                break;
            case DefMethod:
                stringBuilder.append(methodFormat(indentString, tree));
                break;
            case Body:
                formatChildren(indentString, stringBuilder, tree);
                break;
            case ReturnType:
            case Parameters:
            case SuperClass:
            case Modifiers:
                //no op
                break;
            default:
        }
        return stringBuilder.toString();
    }

    private String sourceFileFormat(String indentString, Tree tree) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indentString).append(tree.getNodeType().name()).append(" ").append(tree.getName()).append(" {");
        formatChildren(indentString, stringBuilder, tree);
        stringBuilder.append("\n").append(indentString).append("}");
        return stringBuilder.toString();
    }

    private String classFormat(String indentString, Tree tree) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indentString);
        formatModifier(stringBuilder, tree);
        stringBuilder.append(" class ").append(tree.getName());
        formatSuperClass(stringBuilder, tree);
        stringBuilder.append(" {");
        formatChildren(indentString, stringBuilder, tree);
        stringBuilder.append("\n").append(indentString).append("}\n");
        return stringBuilder.toString();
    }

    private String methodFormat(String indentString, Tree tree) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(indentString);
        formatModifier(stringBuilder, tree);
        stringBuilder.append(" ");
        formatReturnType(stringBuilder, tree);
        stringBuilder.append(" ").append(tree.getName());
        stringBuilder.append("(");
        formatParameters(stringBuilder, tree);
        stringBuilder.append(")");
        stringBuilder.append(" {");
        formatChildren(indentString, stringBuilder, tree);
        stringBuilder.append("\n").append(indentString).append("}");
        return stringBuilder.toString();
    }

    private void formatParameters(StringBuilder stringBuilder, Tree tree) {
        if (tree.hasChildren(NodeType.Parameters)) {
            Tree parameters = tree.findFirstFromChildren(NodeType.Parameters).orElseThrow(IllegalStateException::new);
            boolean isFirst = true;
            for (Tree parameter : parameters.getChildren()) {
                if (!isFirst) {
                    stringBuilder.append(", ");
                    isFirst = false;
                }
                Tree typeName = parameter.findFirstFromChildren(NodeType.TypeName).orElseThrow(IllegalStateException::new);
                stringBuilder.append(typeName.getName()).append(" ").append(parameter.getName());
            }
        }
    }

    private String compilationUnitFormat(String indentString, Tree tree) {
        StringBuilder builder = new StringBuilder();
        builder.append(indentString).append(tree.getNodeType().name()).append(tree.getName()).append(" {");
        formatChildren(indentString, builder, tree);
        builder.append("\n").append(indentString).append("}");
        return builder.toString();
    }

    private void formatModifier(StringBuilder builder, Tree tree) {
        if (tree.hasChildren(NodeType.Modifiers)) {
            Tree modifiers = tree.findFirstFromChildren(NodeType.Modifiers).orElseThrow(IllegalStateException::new);
            builder.append(modifiers.getChildren().stream().map(Tree::getName).collect(Collectors.joining(" ")));
        }
    }

    private void formatSuperClass(StringBuilder builder, Tree tree) {
        if (tree.hasChildren(NodeType.SuperClass)) {
            Tree typeName = tree.findFirstFromChildren(NodeType.SuperClass).orElseThrow(IllegalStateException::new).findFirstFromChildren(NodeType.TypeName).orElseThrow(IllegalStateException::new);
            builder.append(" extends ").append(typeName.getName());
        }
    }

    private void formatReturnType(StringBuilder builder, Tree tree) {
        if (tree.hasChildren(NodeType.ReturnType)) {
            Tree returnTypeName = tree.findFirstFromChildren(NodeType.ReturnType).orElseThrow(IllegalStateException::new).findFirstFromChildren(NodeType.TypeName).orElseThrow(IllegalStateException::new);
            builder.append(returnTypeName.getName());
        }
    }

    private void formatChildren(String indentString, StringBuilder builder, Tree tree) {
        builder.append("\n");
        builder.append(
                tree.getChildren()
                        .stream()
                        .map(child -> format(indentString + "  ", child))
                        .filter(f -> !f.isEmpty())
                        .collect(Collectors.joining(";\n")));
    }

    private String rawFormat(Tree tree) {
        return tree.getNodeType().name();
    }
}
