package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

public abstract class AbstractTreeWalker {

  protected void invokeEventMethod(Tree tree) {
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
    } else if (tree.getNodeType() == NodeType.VarName) {
      onVarName(tree);
    } else if (tree.getNodeType() == NodeType.PrimitiveTypeName) {
      onPrimitiveTypeName(tree);
    } else if (tree.getNodeType() == NodeType.PackageName) {
      onPackageName(tree);
    } else if (tree.getNodeType() == NodeType.ClassName) {
      onClassName(tree);
    } else if (tree.getNodeType() == NodeType.FieldName) {
      onFieldName(tree);
    } else if (tree.getNodeType() == NodeType.MethodName) {
      onMethodName(tree);
    } else {
      throw new IllegalStateException(tree.getNodeType().name());
    }
  }

  public abstract void onFieldName(Tree tree);

  public abstract void onMethodName(Tree tree);

  public abstract void onPackageName(Tree tree);

  public abstract void onClassName(Tree tree);

  public abstract void onPrimitiveTypeName(Tree tree);

  public abstract void onVarName(Tree tree);

  public abstract void onException(Tree tree, Exception e);

  public abstract void onBinding(Tree tree);

  public abstract void onArrayTypeName(Tree tree);

  public abstract void onEveryTree(Tree tree);

  public abstract void onBinOp(Tree tree);

  public abstract void onFlatApply(Tree tree);

  public abstract void onAssign(Tree tree);

  public abstract void onConstructor(Tree tree);

  public abstract void onModule(Tree tree);

  public abstract void onPackage(Tree tree);

  public abstract void onArgument(Tree tree);

  public abstract void onArguments(Tree tree);

  public abstract void onBody(Tree tree);

  public abstract void onModifiers(Tree tree);

  public abstract void onSuperClass(Tree tree);

  public abstract void onIdentifier(Tree tree);

  public abstract void onParameter(Tree tree);

  public abstract void onParameters(Tree tree);

  public abstract void onReturnType(Tree tree);

  public abstract void onTypeName(Tree tree);

  public abstract void onModifier(Tree tree);

  public abstract void onThis(Tree tree);

  public abstract void onSourceFile(Tree tree);

  public abstract void onStringLiteral(Tree tree);

  public abstract void onSelect(Tree tree);

  public abstract void onIntLiteral(Tree tree);

  public abstract void onErrorTree(Tree tree);

  public abstract void onDefVar(Tree tree);

  public abstract void onDefMethod(Tree tree);

  public abstract void onDefClass(Tree tree);

  public abstract void onCompilationUnit(Tree tree);

  public abstract void onApply(Tree tree);

  public abstract void onNullTree();
}
