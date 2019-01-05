package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.CompilationUnit;
import static org.karaffe.compiler.tree.NodeType.DefClass;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Module;
import static org.karaffe.compiler.tree.NodeType.Package;
import static org.karaffe.compiler.tree.NodeType.Parameter;
import static org.karaffe.compiler.tree.NodeType.Parameters;
import static org.karaffe.compiler.tree.NodeType.ReturnType;
import static org.karaffe.compiler.tree.NodeType.Select;
import static org.karaffe.compiler.tree.NodeType.SourceFile;
import static org.karaffe.compiler.tree.NodeType.SuperClass;
import static org.karaffe.compiler.tree.NodeType.TypeName;

public class TreeSchemaValidator extends TreeWalkerAdapter {

  @Override
  void onEveryTree(Tree tree) {
    assert tree.getNodeType() == CompilationUnit || tree.getParent() != null;
  }

  @Override
  public void onCompilationUnit(Tree tree) {
    assert tree.getName().isEmpty();
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(Module);
    }
  }

  @Override
  public void onModule(Tree tree) {
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(Package);
    }
  }

  @Override
  public void onPackage(Tree tree) {
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(SourceFile);
    }
  }

  @Override
  public void onTypeName(Tree tree) {
    assert !tree.getName().isEmpty();
    assert !tree.hasChildren();
  }

  @Override
  public void onModifier(Tree tree) {
    String name = tree.getName();
    assert !name.isEmpty();
    assert name.equals("public") || name.equals("static");
    assert !tree.hasChildren();
  }

  @Override
  public void onThis(Tree tree) {
    assert tree.getName().isEmpty();
    assert !tree.hasChildren();
  }

  @Override
  public void onIdentifier(Tree tree) {
    assert !tree.getName().isEmpty();
    assert !tree.hasChildren();
  }

  @Override
  public void onSourceFile(Tree tree) {
    assert !tree.getName().isEmpty();
    for (Tree child : tree.getChildren()) {
      assert child.getNodeType().equals(DefClass);
    }
  }

  @Override
  public void onStringLiteral(Tree tree) {
    assert !tree.hasChildren();
  }

  @Override
  public void onReturnType(Tree tree) {
    assert tree.indexOf(TypeName) == 0;
    assert tree.getChildren().size() == 1;
  }

  @Override
  public void onSuperClass(Tree tree) {
    assert tree.indexOf(TypeName) == 0;
    assert tree.getChildren().size() == 1;
  }

  @Override
  public void onParameter(Tree tree) {
    assert !tree.getName().isEmpty();
    assert tree.indexOf(TypeName) == 0;
    assert tree.getChildren().size() == 1;
  }

  @Override
  public void onParameters(Tree tree) {
    for (Tree parameter : tree.getChildren()) {
      assert parameter.getNodeType() == Parameter;
    }
  }

  @Override
  public void onApply(Tree tree) {
    assert tree.indexOf(Select) == 0;
    assert tree.indexOf(Arguments) == 1;
    assert tree.getChildren().size() == 2;
  }

  @Override
  public void onSelect(Tree tree) {
    assert tree.indexOf(Identifier) == 0;
    assert tree.getChildren().size() == 1 || tree.getChildren().size() == 2;
  }

  @Override
  public void onIntLiteral(Tree tree) {
    assert !tree.hasChildren();
  }

  @Override
  public void onErrorTree(Tree tree) {

  }

  @Override
  public void onDefVar(Tree tree) {
    assert !tree.getName().isEmpty();
  }

  @Override
  public void onDefMethod(Tree tree) {
    assert tree.indexOf(Modifiers) == 0;
    assert tree.indexOf(ReturnType) == 1;
    assert tree.indexOf(Parameters) == 2;
    assert tree.indexOf(Body) == 3;
    assert tree.getChildren().size() == 4;
  }

  @Override
  public void onDefClass(Tree tree) {
    assert tree.indexOf(SuperClass) == 0;
    assert tree.indexOf(Modifiers) == 1;
    assert tree.indexOf(Body) == 2;
    assert tree.getChildren().size() == 3;
  }

  @Override
  public void onModifiers(Tree tree) {
    assert tree.indexOf(Modifier) == 0;
  }

  @Override
  public void onArgument(Tree tree) {
    assert tree.getChildren().size() == 1;
  }

  @Override
  public void onArguments(Tree tree) {
    assert tree.getName().isEmpty();
  }

  @Override
  public void onBody(Tree tree) {
    assert tree.getName().isEmpty();
  }

  @Override
  public void onNullTree() {
    assert false;
  }
}
