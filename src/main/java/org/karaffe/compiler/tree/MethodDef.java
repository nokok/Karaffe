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

    public Node findModifierNode() {
        return getChildren().get(0);
    }

    public Node findNameNode() {
        return getChildren().get(1);
    }

    public Node findParameterNode() {
        return getChildren().get(2);
    }

    public Node findReturnTypeNode() {
        return getChildren().get(3);
    }

    public Node findMethodBodyNode() {
        return getChildren().get(4);
    }

    public String getMethodName() {
        return ((Name) findNameNode()).getText();
    }

    public Parameters getParameters() {
        return (Parameters) this.findParameterNode();
    }

    public String getReturnName() {
        return ((TypeName) findReturnTypeNode()).getText();
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
