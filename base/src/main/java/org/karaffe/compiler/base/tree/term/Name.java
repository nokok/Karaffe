package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.Tree;

public interface Name extends CharSequence, Tree, Term {
    NameType getType();
}
