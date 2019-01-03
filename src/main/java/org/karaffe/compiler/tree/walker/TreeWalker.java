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
        }
    }

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
