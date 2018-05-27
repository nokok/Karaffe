package org.karaffe.compiler.base.tree.element;

import org.karaffe.compiler.base.tree.Tree;

public interface CompilationUnit extends Tree {
    void addTopLevel(Tree source);
}
