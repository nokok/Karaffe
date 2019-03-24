package org.karaffe.compiler.frontend.karaffe.walker;

import org.karaffe.compiler.tree.Tree;
import org.karaffe.compiler.tree.walker.TreeWalker;

import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.ArrayTypeName;
import static org.karaffe.compiler.tree.NodeType.BinOp;
import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.CompilationUnit;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Module;
import static org.karaffe.compiler.tree.NodeType.Package;
import static org.karaffe.compiler.tree.NodeType.Parameter;
import static org.karaffe.compiler.tree.NodeType.Parameters;
import static org.karaffe.compiler.tree.NodeType.ReturnType;
import static org.karaffe.compiler.tree.NodeType.SourceFile;
import static org.karaffe.compiler.tree.NodeType.SuperClass;
import static org.karaffe.compiler.tree.NodeType.TypeName;
import static org.karaffe.compiler.tree.NodeType.VarName;

public class TreeSchemaValidator extends TreeWalker {

  @Override
  public void onEveryTree(Tree tree) {
    assert tree.getNodeType() == CompilationUnit || tree.getParent() != null : tree.toString();
    assert tree.getName().isEmpty() || tree.getChildren().isEmpty() : tree.getNodeType() + ":" + tree.getName();
  }

  @Override
  public void onCompilationUnit(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(Module) : tree.toString();
    }
  }

  @Override
  public void onModule(Tree tree) {
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(Package) : tree.toString();
    }
  }

  @Override
  public void onPackage(Tree tree) {
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(SourceFile) : tree.toString();
    }
  }

  @Override
  public void onTypeName(Tree tree) {
    assert !tree.getName().isEmpty() : tree.toString();
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onArrayTypeName(Tree tree) {
    assert !tree.getName().isEmpty() : tree.toString();
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onModifier(Tree tree) {
    String name = tree.getName();
    assert !name.isEmpty() : tree.toString();
    assert name.equals("public") || name.equals("static") : tree.toString();
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onThis(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onIdentifier(Tree tree) {
    assert !tree.getName().isEmpty() : tree.toString();
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onSourceFile(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    //assert tree.getChildren().stream().skip(1).allMatch(p -> p.getNodeType() == DefClass);
  }

  @Override
  public void onStringLiteral(Tree tree) {
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onReturnType(Tree tree) {
    assert tree.indexOf(TypeName) == 0 : tree.toString();
    assert tree.getChildren().size() == 1 : tree.toString();
  }

  @Override
  public void onSuperClass(Tree tree) {
    assert tree.indexOf(TypeName) == 0 : tree.toString();
    assert tree.getChildren().size() == 1 : tree.toString();
  }

  @Override
  public void onParameter(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.indexOf(TypeName) == 1 || tree.indexOf(ArrayTypeName) == 1 : tree.toString();
    assert tree.getChildren().size() == 2 : tree.toString();
  }

  @Override
  public void onParameters(Tree tree) {
    for (Tree parameter : tree.getChildren()) {
      assert parameter.getNodeType() == Parameter : tree.toString();
    }
  }

  @Override
  public void onApply(Tree tree) {
    assert tree.indexOf(BinOp) == 1 || tree.indexOf(VarName) == 1 : tree.toString();
    assert tree.indexOf(Arguments) == 2 : tree.toString();
    assert tree.getChildren().size() == 3 : tree.toString();
  }

  @Override
  public void onSelect(Tree tree) {
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.getChildren().size() == 1 || tree.getChildren().size() == 2 : tree.toString();
  }

  @Override
  public void onIntLiteral(Tree tree) {
    assert !tree.hasChildren() : tree.toString();
  }

  @Override
  public void onErrorTree(Tree tree) {

  }

  @Override
  public void onDefVar(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.indexOf(TypeName) == 1 || tree.indexOf(ArrayTypeName) == 1 : tree.toString();
  }

  @Override
  public void onDefMethod(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.indexOf(Modifiers) == 1 : tree.toString();
    assert tree.indexOf(ReturnType) == 2 : tree.toString();
    assert tree.indexOf(Parameters) == 3 : tree.toString();
    assert tree.indexOf(Body) == 4 : tree.toString();
    assert tree.getChildren().size() == 5 : tree.toString();
  }

  @Override
  public void onDefClass(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.indexOf(SuperClass) == 1 : tree.toString();
    assert tree.indexOf(Modifiers) == 2 : tree.toString();
    assert tree.indexOf(Body) == 3 : tree.toString();
    assert tree.getChildren().size() == 4 : tree.toString();
  }

  @Override
  public void onModifiers(Tree tree) {
    assert tree.indexOf(Modifier) == 0 : tree.toString();
  }

  @Override
  public void onArgument(Tree tree) {
    assert tree.getChildren().size() == 1 : tree.toString();
  }

  @Override
  public void onArguments(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
  }

  @Override
  public void onBody(Tree tree) {
    assert tree.getName().isEmpty() : tree.toString();
  }

  @Override
  public void onBinding(Tree tree) {
    assert tree.indexOf(Identifier) == 0 : tree.toString();
    assert tree.indexOf(TypeName) == 1 || tree.indexOf(ArrayTypeName) == 1 : tree.toString();
  }

  @Override
  public void onConstructor(Tree tree) {
    assert tree.indexOf(Modifiers) == 0 : tree.toString();
    assert tree.indexOf(Parameters) == 1 : tree.toString();
    assert tree.indexOf(Body) == 2 : tree.toString();
  }

  @Override
  public void onVarName(Tree tree) {
    assert !tree.getName().isEmpty();
    assert tree.getChildren().isEmpty();
  }

  @Override
  public void onNullTree() {
    assert false;
  }

  @Override
  public void onEmpty(Tree tree) {
    assert tree.getChildren().isEmpty() : tree.toString();
  }
}
