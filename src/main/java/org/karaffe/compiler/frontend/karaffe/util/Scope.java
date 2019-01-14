package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Scope {
  private Scope parent;
  private Map<String, Tree> names = new HashMap<>();

  public Scope() {
    this(null);
  }

  public Scope(Scope parent) {
    this.parent = parent;
  }

  public void add(String name, Tree tree) {
    this.names.put(Objects.requireNonNull(name), Objects.requireNonNull(tree));
  }

  public boolean hasName(String name) {
    return this.names.containsKey(name);
  }

  public Optional<Tree> lookup(String name) {
    if (this.hasName(name)) {
      return Optional.of(this.names.get(name));
    }
    if (this.parent == null) {
      return Optional.empty();
    } else {
      return this.parent.lookup(name);
    }
  }
}
