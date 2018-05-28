package org.karaffe.compiler.base.tree;

public interface Trees {
    static Tree.CompilationUnit compilationUnit() {
        return new Tree.CompilationUnit();
    }

    static Tree.Template template() {
        return new Tree.Template();
    }
}
