package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

public abstract class TreeWalker {

  public final void walk(Tree tree) {
    try {
      walk1(tree);
    } catch (TreeWalkCancellationException e) {
      // ignore
    } catch (RuntimeException e) {
      onException(tree, e);
      throw e;
    }
  }

  private void walk1(Tree tree) {
    if (tree == null) {
      onNullTree();
      return;
    }
    onEveryTree(tree);
    if (tree.hasChildren()) {
      for (Tree child : tree.getChildren()) {
        walk1(child);
      }
    }
    if (tree.getNodeType() == NodeType.Apply) {
      onApply(tree);
    } else if (tree.getNodeType() == NodeType.CompilationUnit) {
      onCompilationUnit(tree);
    } else if (tree.getNodeType() == NodeType.DefClass) {
      onDefClass(tree);
    } else if (tree.getNodeType() == NodeType.DefMethod) {
      onDefMethod(tree);
    } else if (tree.getNodeType() == NodeType.DefVar) {
      onDefVar(tree);
    } else if (tree.getNodeType() == NodeType.Error) {
      onErrorTree(tree);
    } else if (tree.getNodeType() == NodeType.IntLiteral) {
      onIntLiteral(tree);
    } else if (tree.getNodeType() == NodeType.Select) {
      onSelect(tree);
    } else if (tree.getNodeType() == NodeType.StringLiteral) {
      onStringLiteral(tree);
    } else if (tree.getNodeType() == NodeType.SourceFile) {
      onSourceFile(tree);
    } else if (tree.getNodeType() == NodeType.This) {
      onThis(tree);
    } else if (tree.getNodeType() == NodeType.Modifier) {
      onModifier(tree);
    } else if (tree.getNodeType() == NodeType.Modifiers) {
      onModifiers(tree);
    } else if (tree.getNodeType() == NodeType.SuperClass) {
      onSuperClass(tree);
    } else if (tree.getNodeType() == NodeType.Identifier) {
      onIdentifier(tree);
    } else if (tree.getNodeType() == NodeType.Parameter) {
      onParameter(tree);
    } else if (tree.getNodeType() == NodeType.Parameters) {
      onParameters(tree);
    } else if (tree.getNodeType() == NodeType.ReturnType) {
      onReturnType(tree);
    } else if (tree.getNodeType() == NodeType.TypeName) {
      onTypeName(tree);
    } else if (tree.getNodeType() == NodeType.Body) {
      onBody(tree);
    } else if (tree.getNodeType() == NodeType.Arguments) {
      onArguments(tree);
    } else if (tree.getNodeType() == NodeType.Argument) {
      onArgument(tree);
    } else if (tree.getNodeType() == NodeType.Package) {
      onPackage(tree);
    } else if (tree.getNodeType() == NodeType.Module) {
      onModule(tree);
    } else if (tree.getNodeType() == NodeType.Constructor) {
      onConstructor(tree);
    } else if (tree.getNodeType() == NodeType.Assign) {
      onAssign(tree);
    } else if (tree.getNodeType() == NodeType.FlatApply) {
      onFlatApply(tree);
    } else if (tree.getNodeType() == NodeType.BinOp) {
      onBinOp(tree);
    } else {
      throw new IllegalStateException(tree.getNodeType().name());
    }
  }

  protected final void bailout() {
    throw new TreeWalkCancellationException();
  }

  abstract void onException(Tree tree, Exception e);

  abstract void onEveryTree(Tree tree);

  abstract void onBinOp(Tree tree);

  abstract void onFlatApply(Tree tree);

  abstract void onAssign(Tree tree);

  abstract void onConstructor(Tree tree);

  abstract void onModule(Tree tree);

  abstract void onPackage(Tree tree);

  abstract void onArgument(Tree tree);

  abstract void onArguments(Tree tree);

  abstract void onBody(Tree tree);

  abstract void onModifiers(Tree tree);

  abstract void onSuperClass(Tree tree);

  abstract void onIdentifier(Tree tree);

  abstract void onParameter(Tree tree);

  abstract void onParameters(Tree tree);

  abstract void onReturnType(Tree tree);

  abstract void onTypeName(Tree tree);

  abstract void onModifier(Tree tree);

  abstract void onThis(Tree tree);

  abstract void onSourceFile(Tree tree);

  abstract void onStringLiteral(Tree tree);

  abstract void onSelect(Tree tree);

  abstract void onIntLiteral(Tree tree);

  abstract void onErrorTree(Tree tree);

  abstract void onDefVar(Tree tree);

  abstract void onDefMethod(Tree tree);

  abstract void onDefClass(Tree tree);

  abstract void onCompilationUnit(Tree tree);

  abstract void onApply(Tree tree);

  abstract void onNullTree();
}
