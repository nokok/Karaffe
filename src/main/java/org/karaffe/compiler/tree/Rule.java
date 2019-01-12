package org.karaffe.compiler.tree;

public interface Rule<R> {
  R apply(Tree tree);
}
