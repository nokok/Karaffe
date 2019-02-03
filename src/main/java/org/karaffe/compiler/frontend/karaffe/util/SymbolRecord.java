package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.Position;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SymbolRecord {
  private final List<KaraffeModifierType> modifiers;
  private final SymbolType symbolType;
  private final Position position;
  private final String name;
  private final Tree type;

  private SymbolRecord(List<KaraffeModifierType> modifiers, SymbolType symbolType, Position position, String name, Tree type) {
    this.modifiers = Objects.requireNonNull(modifiers);
    this.symbolType = Objects.requireNonNull(symbolType);
    this.position = Objects.requireNonNull(position);
    this.name = Objects.requireNonNull(name);
    this.type = Objects.requireNonNull(type);
  }

  public List<KaraffeModifierType> getModifiers() {
    return modifiers;
  }

  public SymbolType getSymbolType() {
    return symbolType;
  }

  public Position getPosition() {
    return position;
  }

  public String getName() {
    return name;
  }

  public Tree getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SymbolRecord that = (SymbolRecord) o;
    return modifiers.equals(that.modifiers) &&
      symbolType == that.symbolType &&
      position.equals(that.position) &&
      name.equals(that.name) &&
      type.equals(that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modifiers, symbolType, position, name, type);
  }

  public static SymbolRecord get(KaraffeModifierType modifier, SymbolType symbolType, Position position, String name, Tree type) {
    return new SymbolRecord(Collections.singletonList(modifier), symbolType, position, name, type);
  }

  public static SymbolRecord get(List<KaraffeModifierType> modifiers, SymbolType symbolType, Position position, String name, Tree type) {
    return new SymbolRecord(modifiers, symbolType, position, name, type);
  }

  @Override
  public String toString() {
    String mod = modifiers.stream().map(Object::toString).map(String::toLowerCase).collect(Collectors.joining(","));
    return String.format("%s/%s/%s %s:%s", mod, position, symbolType.name().toLowerCase(), name, type);
  }
}
