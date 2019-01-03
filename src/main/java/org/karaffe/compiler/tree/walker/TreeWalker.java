package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

public abstract class TreeWalker {
    public void walk(Tree tree) {
        if (tree == null) {
            onNullTree();
            return;
        }
        if (tree.hasChildren()) {
            for (Tree child : tree.getChildren()) {
                walk(child);
            }
        }
        switch (tree.getNodeType()) {
            case Apply:
                onApply(tree);
                break;
            case CompilationUnit:
                onCompilationUnit(tree);
                break;
            case DefClass:
                onDefClass(tree);
                break;
            case DefMethod:
                onDefMethod(tree);
                break;
            case DefVar:
                onDefVar(tree);
                break;
            case Error:
                onErrorTree(tree);
                break;
            case IntLiteral:
                onIntLiteral(tree);
                break;
            case Select:
                onSelect(tree);
                break;
            case StringLiteral:
                onStringLiteral(tree);
                break;
            case SourceFile:
                onSourceFile(tree);
                break;
            case This:
                onThis(tree);
                break;
            case Modifier:
                onModifier(tree);
                break;
            case Modifiers:
                onModifiers(tree);
                break;
            case SuperClass:
                onSuperClass(tree);
                break;
            case Identifier:
                onIdentifier(tree);
                break;
            case Parameter:
                onParameter(tree);
                break;
            case Parameters:
                onParameters(tree);
                break;
            case ReturnType:
                onReturnType(tree);
                break;
            case TypeName:
                onType(tree);
                break;
            default:
                throw new IllegalStateException(tree.getNodeType().name());
        }
    }

    abstract void onModifiers(Tree tree);

    abstract void onSuperClass(Tree tree);

    abstract void onIdentifier(Tree tree);

    abstract void onParameter(Tree tree);

    abstract void onParameters(Tree tree);

    abstract void onReturnType(Tree tree);

    abstract void onType(Tree tree);

    abstract void onModifier(Tree tree);

    abstract void onThis(Tree tree);

    abstract void onSourceFile(Tree tree);

    abstract void onStringLiteral(Tree tree);

    abstract void onSelect(Tree tree);

    abstract void onIntLiteral(Tree tree);

    abstract void onErrorTree(Tree tree);

    abstract void onDefVar(Tree tree);

    abstract void onDefMethod(Tree tree);

    abstract void onDefClass(Tree tree);

    abstract void onCompilationUnit(Tree tree);

    abstract void onApply(Tree tree);

    abstract void onNullTree();
}
