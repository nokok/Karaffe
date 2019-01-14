package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.Tree;

import static org.karaffe.compiler.tree.NodeType.PrimitiveTypeName;

public class BuiltIn extends Scope {
  public BuiltIn() {
    this.add("int", new Tree(PrimitiveTypeName));
    this.add("boolean", new Tree(PrimitiveTypeName));
    this.add("char", new Tree(PrimitiveTypeName));
    this.add("byte", new Tree(PrimitiveTypeName));
    this.add("short", new Tree(PrimitiveTypeName));
    this.add("int", new Tree(PrimitiveTypeName));
    this.add("long", new Tree(PrimitiveTypeName));
    this.add("float", new Tree(PrimitiveTypeName));
    this.add("double", new Tree(PrimitiveTypeName));
  }
}
