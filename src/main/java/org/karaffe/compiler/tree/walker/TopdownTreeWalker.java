package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

public abstract class TopdownTreeWalker extends TreeWalker {

  @Override
  protected void walk1(Tree tree) {
    if (tree == null) {
      onNullTree();
      return;
    }
    onEveryTree(tree);
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
    } else if (tree.getNodeType() == NodeType.DefConstructor) {
      onConstructor(tree);
    } else if (tree.getNodeType() == NodeType.Assign) {
      onAssign(tree);
    } else if (tree.getNodeType() == NodeType.FlatApply) {
      onFlatApply(tree);
    } else if (tree.getNodeType() == NodeType.BinOp) {
      onBinOp(tree);
    } else if (tree.getNodeType() == NodeType.ArrayTypeName) {
      onArrayTypeName(tree);
    } else if (tree.getNodeType() == NodeType.Binding) {
      onBinding(tree);
    } else {
      throw new IllegalStateException(tree.getNodeType().name());
    }
    if (tree.hasChildren()) {
      for (Tree child : tree.getChildren()) {
        walk1(child);
      }
    }
  }
}
