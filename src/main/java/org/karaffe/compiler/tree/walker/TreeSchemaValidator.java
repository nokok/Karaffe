package org.karaffe.compiler.tree.walker;

import org.karaffe.compiler.tree.Tree;

import static org.karaffe.compiler.tree.NodeType.Body;
import static org.karaffe.compiler.tree.NodeType.DefClass;
import static org.karaffe.compiler.tree.NodeType.Identifier;
import static org.karaffe.compiler.tree.NodeType.Modifier;
import static org.karaffe.compiler.tree.NodeType.Modifiers;
import static org.karaffe.compiler.tree.NodeType.Select;
import static org.karaffe.compiler.tree.NodeType.SourceFile;
import static org.karaffe.compiler.tree.NodeType.SuperClass;
import static org.karaffe.compiler.tree.NodeType.TypeName;

public class TreeSchemaValidator extends TreeWalkerAdapter {

    @Override
    void onCompilationUnit(Tree tree) {
        assert tree.getName().isEmpty();
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(SourceFile);
        }
    }

    @Override
    void onTypeName(Tree tree) {
        assert !tree.getName().isEmpty();
    }

    @Override
    void onIdentifier(Tree tree) {
        assert !tree.getName().isEmpty();
    }

    @Override
    void onSourceFile(Tree tree) {
        assert !tree.getName().isEmpty();
        for (Tree child : tree.getChildren()) {
            assert child.getNodeType().equals(DefClass);
        }
    }

    @Override
    void onReturnType(Tree tree) {
        assert tree.indexOf(TypeName) == 0;
    }

    @Override
    void onSuperClass(Tree tree) {
        assert tree.indexOf(TypeName) == 0;
    }

    @Override
    void onParameter(Tree tree) {
        assert !tree.getName().isEmpty();
        assert tree.indexOf(TypeName) == 0;
    }

    @Override
    void onApply(Tree tree) {
        assert tree.indexOf(Select) == 0;
    }

    @Override
    void onSelect(Tree tree) {
        assert tree.indexOf(Identifier) == 0;
    }

    @Override
    void onDefClass(Tree tree) {
        assert tree.indexOf(SuperClass) == 0;
        assert tree.indexOf(Modifiers) == 1;
        assert tree.indexOf(Body) == 2;
    }

    @Override
    void onModifiers(Tree tree) {
        assert tree.indexOf(Modifier) == 0;
    }
}
