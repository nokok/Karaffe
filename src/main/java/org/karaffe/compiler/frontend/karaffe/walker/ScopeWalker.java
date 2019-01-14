package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.frontend.karaffe.util.Scope;
import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TopdownTreeWalker;
import org.karaffe.compiler.util.CompilerContext;

import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

public class ScopeWalker extends TopdownTreeWalker {
  private final CompilerContext context;
  private Scope rootScope = new Scope();
  private Stack<Scope> scopes = new Stack<>();

  public ScopeWalker(CompilerContext context) {
    this.context = context;
    this.scopes.push(rootScope);
    rootScope.add("java.lang.Object", new Tree(NodeType.TypeName));
    rootScope.add("int", new Tree(NodeType.TypeName));
    rootScope.add("void", new Tree(NodeType.TypeName));
  }

  private void createName(String name, Tree tree) {
    Scope peek = this.scopes.peek();
    if (peek == null) {
      return;
    }
    peek.add(Objects.requireNonNull(name), Objects.requireNonNull(tree));
  }

  @Override
  public void onDefClass(Tree tree) {
    this.scopes.push(new Scope(scopes.peek()));
    createName(tree.forceDig(NodeType.Identifier).getName(), tree);
  }

  @Override
  public void onDefVar(Tree tree) {
    createName(tree.forceDig(NodeType.Identifier).getName(), tree);
  }

  @Override
  public void onConstructor(Tree tree) {
    createName("<init>", tree);
  }

  @Override
  public void onVarName(Tree tree) {
    Scope scope = this.scopes.peek();
    Optional<Tree> result = scope.lookup(tree.getName());
    result.ifPresentOrElse(r -> System.out.println(r.getName()), () -> System.out.println("Not found. : " + tree.getName()));
  }

  @Override
  public void onTypeName(Tree tree) {
    Scope scope = this.scopes.peek();
    Optional<Tree> result = scope.lookup(tree.getName());
    result.ifPresentOrElse(r -> System.out.println(r.getName()), () -> System.out.println("Not found. : " + tree.getName()));
  }
}
