package org.karaffe.compiler.frontend.karaffe.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scope {
  private List<SymbolRecord> symbols = new ArrayList<>();

  public void add(SymbolRecord symbolRecord) {
    this.symbols.add(Objects.requireNonNull(symbolRecord));
  }
}
