package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

public class PrintTreeWalker extends TreeWalker {
    @Override
    void onThis(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onSourceFile(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onStringLiteral(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onSelect(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onIntLiteral(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onErrorTree(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onDefVar(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onDefMethod(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onDefClass(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onCompilationUnit(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onApply(Tree tree) {
        System.out.println(tree.getName());
    }

    @Override
    void onNullTree() {
        System.out.println("<null>");
    }
}
