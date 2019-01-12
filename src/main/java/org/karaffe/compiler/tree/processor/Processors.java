package org.karaffe.compiler.tree.processor;

import org.karaffe.compiler.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Processors<R> {
  private final List<Processor<R>> rules = new ArrayList<>();

  public void add(Processor<R> rule) {
    this.rules.add(Objects.requireNonNull(rule));
  }

  public Optional<Processor<R>> findFirstApplicable(Tree tree) {
    return this.rules.stream().filter(r -> r.isApplicableTo(tree)).findFirst();
  }
}
