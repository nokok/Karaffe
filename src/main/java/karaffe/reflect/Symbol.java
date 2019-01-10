package karaffe.reflect;

import java.util.Objects;

public class Symbol {
  private SymbolType symbolType;
  private String name;

  private Symbol(SymbolType symbolType, String name) {
    this.symbolType = Objects.requireNonNull(symbolType);
    this.name = Objects.requireNonNull(name);
  }

  public static Symbol of(SymbolType symbolType, String name) {
    return new Symbol(symbolType, name);
  }

  @Override
  public String toString() {
    return this.symbolType.name() + " " + this.name;
  }
}
