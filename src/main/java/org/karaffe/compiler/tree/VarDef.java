package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class VarDef extends AbstractNode {

    public VarDef(final Modifiers modifiers, final Name name, final TypeName type) {
        super(NodeType.DEFVAR, new ArrayList<>(Arrays.asList(modifiers, name, type)));
    }

    public VarDef(final Modifiers modifiers, final Name name, final TypeName type, Node initializer) {
        super(NodeType.DEFVAR, new ArrayList<>(Arrays.asList(modifiers, name, type, initializer)));
    }

    public boolean has(final Class<? extends ModifierToken> modifier) {
        return ((Modifiers) this.getChildren().get(0)).stream().filter(t -> t.getClass().equals(modifier)).count() != 0;
    }

    public String getName() {
        return ((Name) this.getChildren().get(1)).getText();
    }

    public String getTypeName() {
        return ((TypeName) this.getChildren().get(2)).getText();
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
