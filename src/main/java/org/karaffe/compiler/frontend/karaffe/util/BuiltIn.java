package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.TreeFactory;

import static org.karaffe.compiler.tree.NodeType.PrimitiveTypeName;

public class BuiltIn extends Scope {
  public BuiltIn() {
    this.add("int", TreeFactory.newTree(PrimitiveTypeName));
    this.add("boolean", TreeFactory.newTree(PrimitiveTypeName));
    this.add("char", TreeFactory.newTree(PrimitiveTypeName));
    this.add("byte", TreeFactory.newTree(PrimitiveTypeName));
    this.add("short", TreeFactory.newTree(PrimitiveTypeName));
    this.add("int", TreeFactory.newTree(PrimitiveTypeName));
    this.add("long", TreeFactory.newTree(PrimitiveTypeName));
    this.add("float", TreeFactory.newTree(PrimitiveTypeName));
    this.add("double", TreeFactory.newTree(PrimitiveTypeName));
  }
}
