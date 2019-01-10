package org.karaffe.compiler.frontend.karaffe.typechecker;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.util.CompilerContext;
import org.karaffe.compiler.util.TypeInfo;

import java.util.Objects;

public class TypeChecker {

  private final CompilerContext context;

  public TypeChecker(CompilerContext context) {
    this.context = Objects.requireNonNull(context);
  }

  public TypeInfo unify(Tree tree) {
    switch (tree.getNodeType()) {
    case IntLiteral:
      return new TypeInfo(int.class);
    case StringLiteral:
      return new TypeInfo(String.class);
    case CompilationUnit:
    case DefClass:
    case DefMethod:
    case DefVar:
      return new TypeInfo(Void.class);
    case Error:
      return TypeInfo.error();
    default:
      throw new IllegalStateException(tree.getNodeType().name());
    }
  }
}
