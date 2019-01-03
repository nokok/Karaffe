package org.karaffe.compiler.tree.walker;

public class PrintTreeWalker extends TreeWalkerAdapter {

    @Override
    void onNullTree() {
        System.out.println("<null>");
    }
}
