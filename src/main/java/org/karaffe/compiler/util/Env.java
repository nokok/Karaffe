package org.karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;

public class Env {
  private List<String> names = new ArrayList<>();
  private Env parent;

  public Env getParent() {
    return parent;
  }

  public List<String> getNames() {
    return this.names;
  }

  public void addName(String name) {
    this.names.add(name);
  }

  @Override
  public String toString() {
    return this.names.toString();
  }
}
