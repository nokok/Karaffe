package org.karaffe.compiler.frontend.karaffe.util;

import org.karaffe.compiler.tree.TreeFactory;
import org.karaffe.compiler.util.Position;

import static org.karaffe.compiler.frontend.karaffe.util.KaraffeModifierType.PUBLIC;
import static org.karaffe.compiler.frontend.karaffe.util.SymbolType.CLASS;
import static org.karaffe.compiler.frontend.karaffe.util.SymbolType.METHOD;
import static org.karaffe.compiler.tree.NodeType.ArrayTypeName;
import static org.karaffe.compiler.tree.NodeType.ClassName;
import static org.karaffe.compiler.tree.NodeType.DefMethod;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Parameter;
import static org.karaffe.compiler.tree.NodeType.Parameters;
import static org.karaffe.compiler.tree.NodeType.PrimitiveTypeName;
import static org.karaffe.compiler.tree.NodeType.ReturnType;
import static org.karaffe.compiler.tree.NodeType.TypeName;

class BuiltIn extends Scope {
  BuiltIn() {
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Boolean", TreeFactory.newTree(PrimitiveTypeName, "boolean")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Byte", TreeFactory.newTree(PrimitiveTypeName, "byte")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Char", TreeFactory.newTree(PrimitiveTypeName, "char")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Short", TreeFactory.newTree(PrimitiveTypeName, "short")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Int", TreeFactory.newTree(PrimitiveTypeName, "int")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Long", TreeFactory.newTree(PrimitiveTypeName, "long")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Float", TreeFactory.newTree(PrimitiveTypeName, "float")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Double", TreeFactory.newTree(PrimitiveTypeName, "double")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Unit", TreeFactory.newTree(PrimitiveTypeName, "void")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "Object", TreeFactory.newTree(ClassName, "java.lang.Object")));
    this.add(SymbolRecord.get(PUBLIC, CLASS, Position.noPos(), "String", TreeFactory.newTree(ClassName, "java.lang.String")));
    this.add(SymbolRecord.get(PUBLIC, METHOD, Position.noPos(), "println", TreeFactory.newTree(DefMethod,
      TreeFactory.newTree(Identifier, "main"),
      TreeFactory.newTree(Modifiers, TreeFactory.newTree(Modifier, "public"), TreeFactory.newTree(Modifier, "static")),
      TreeFactory.newTree(ReturnType, TreeFactory.newTree(TypeName, "Unit")),
      TreeFactory.newTree(Parameters, TreeFactory.newTree(Parameter,
        TreeFactory.newTree(Identifier, "args"),
        TreeFactory.newTree(ArrayTypeName, "java.lang.String")
      ))
    )));
  }
}
