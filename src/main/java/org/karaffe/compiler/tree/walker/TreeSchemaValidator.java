package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.NodeType;
import org.karaffe.compiler.tree.Tree;

import static org.karaffe.compiler.tree.NodeType.Arguments;
import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.DefClass;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Parameter;
import static org.karaffe.compiler.tree.NodeType.Parameters;
import static org.karaffe.compiler.tree.NodeType.ReturnType;
import static org.karaffe.compiler.tree.NodeType.Select;
import static org.karaffe.compiler.tree.NodeType.Package;
import static org.karaffe.compiler.tree.NodeType.Module;
import static org.karaffe.compiler.tree.NodeType.SourceFile;
import static org.karaffe.compiler.tree.NodeType.SuperClass;
import static org.karaffe.compiler.tree.NodeType.TypeName;

public class TreeSchemaValidator extends TreeWalkerAdapter {

    @Override
    void onCompilationUnit(Tree tree) {
        assert tree.getName().isEmpty();
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(Module);
        }
    }

    @Override
    void onModule(Tree tree) {
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(Package);
        }
    }

    @Override
    void onPackage(Tree tree) {
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(SourceFile);
        }
    }

    @Override
    void onTypeName(Tree tree) {
        assert !tree.getName().isEmpty();
        assert !tree.hasChildren();
    }

    @Override
    void onModifier(Tree tree) {
        String name = tree.getName();
        assert !name.isEmpty();
        assert name.equals("public") || name.equals("static");
        assert !tree.hasChildren();
    }

    @Override
    void onThis(Tree tree) {
        assert tree.getName().isEmpty();
        assert !tree.hasChildren();
    }

    @Override
    void onIdentifier(Tree tree) {
        assert !tree.getName().isEmpty();
        assert !tree.hasChildren();
    }

    @Override
    void onSourceFile(Tree tree) {
        assert !tree.getName().isEmpty();
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(DefClass);
        }
    }

    @Override
    void onStringLiteral(Tree tree) {
        assert !tree.hasChildren();
    }

    @Override
    void onReturnType(Tree tree) {
        assert tree.indexOf(TypeName) == 0;
        assert tree.getChildren().size() == 1;
    }

    @Override
    void onSuperClass(Tree tree) {
        assert tree.indexOf(TypeName) == 0;
        assert tree.getChildren().size() == 1;
    }

    @Override
    void onParameter(Tree tree) {
        assert !tree.getName().isEmpty();
        assert tree.indexOf(TypeName) == 0;
        assert tree.getChildren().size() == 1;
    }

    @Override
    void onParameters(Tree tree) {
        for (Tree parameter : tree.getChildren()) {
            assert parameter.getNodeType() == Parameter;
        }
    }

    @Override
    void onApply(Tree tree) {
        assert tree.indexOf(Select) == 0;
        assert tree.indexOf(Arguments) == 1;
        assert tree.getChildren().size() == 2;
    }

    @Override
    void onSelect(Tree tree) {
        assert tree.indexOf(Identifier) == 0;
        assert tree.getChildren().size() == 1 || tree.getChildren().size() == 2;
    }

    @Override
    void onIntLiteral(Tree tree) {
        assert !tree.hasChildren();
    }

    @Override
    void onErrorTree(Tree tree) {

    }

    @Override
    void onDefVar(Tree tree) {
        assert !tree.getName().isEmpty();
    }

    @Override
    void onDefMethod(Tree tree) {
        assert tree.indexOf(Modifiers) == 0;
        assert tree.indexOf(ReturnType) == 1;
        assert tree.indexOf(Parameters) == 2;
        assert tree.indexOf(Body) == 3;
        assert tree.getChildren().size() == 4;
    }

    @Override
    void onDefClass(Tree tree) {
        assert tree.indexOf(SuperClass) == 0;
        assert tree.indexOf(Modifiers) == 1;
        assert tree.indexOf(Body) == 2;
        assert tree.getChildren().size() == 3;
    }

    @Override
    void onModifiers(Tree tree) {
        assert tree.indexOf(Modifier) == 0;
    }

    @Override
    void onArgument(Tree tree) {
        assert tree.getChildren().size() == 1;
    }

    @Override
    void onArguments(Tree tree) {
        assert tree.getName().isEmpty();
    }

    @Override
    void onBody(Tree tree) {
        assert tree.getName().isEmpty();
    }
}
