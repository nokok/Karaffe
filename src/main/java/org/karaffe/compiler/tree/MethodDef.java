package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class MethodDef extends AbstractNode {

    public MethodDef(final Node modifiers, final Node name, final Node parameters, final Node returnType, final Node block) {
        super(NodeType.DEFMETHOD, modifiers, name, parameters, returnType, block);
    }

    public Modifiers getModifiers() {
        final Modifiers modifiers = (Modifiers) super.getChildren().get(0);
        return modifiers;
    }

    public boolean hasModifier(final Class<? extends ModifierToken> clazz) {
        return this.getModifiers().stream().filter(f -> f.getClass().equals(clazz)).count() == this.getModifiers().size();
    }

    public String getMethodName() {
        return ((Name) super.getChildren().get(1)).getText();
    }

    public Parameters getParameters() {
        return (Parameters) this.getChildren().get(2);
    }

    public String getReturnName() {
        return ((TypeName) super.getChildren().get(3)).getText();
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
