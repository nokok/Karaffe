package org.karaffe.compiler.tree;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class MethodDef extends AbstractNode {

    public MethodDef(final Node modifiers, final Node name, final Node parameters, final Node returnType,
            final Node block) {
        super(NodeType.DEFMETHOD, modifiers, name, parameters, returnType, block);
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public Node findMethodBodyNode() {
        return this.getChildren().get(4);
    }

    public Node findModifierNode() {
        return this.getChildren().get(0);
    }

    public Node findNameNode() {
        return this.getChildren().get(1);
    }

    public Node findParameterNode() {
        return this.getChildren().get(2);
    }

    public Node findReturnTypeNode() {
        return this.getChildren().get(3);
    }

    public String getMethodName() {
        return ((Name) this.findNameNode()).getText();
    }

    public Modifiers getModifiers() {
        final Modifiers modifiers = (Modifiers) super.getChildren().get(0);
        return modifiers;
    }

    public Parameters getParameters() {
        return (Parameters) this.findParameterNode();
    }

    public String getReturnName() {
        return ((TypeName) this.findReturnTypeNode()).getText();
    }

    public boolean hasModifier(final Class<? extends ModifierToken> clazz) {
        return this.getModifiers().stream().filter(f -> f.getClass().equals(clazz)).count() == this.getModifiers()
                .size();
    }

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final Node modifiers = this.findModifierNode();
        final Node name = this.findNameNode();
        final Node parameters = this.findParameterNode();
        final Node returnType = this.findReturnTypeNode();
        final Node block = this.findMethodBodyNode().normalize(context);
        return new MethodDef(modifiers, name, parameters, returnType, block).toNodeList();
    }

    @Override
    public String vSource() {
        return String.format("%s %s %s %s %s", this.findModifierNode().vSource(), this.findReturnTypeNode().vSource(),
                this.findNameNode().vSource(), this.findParameterNode().vSource(), this.findMethodBodyNode().vSource());
    }
}
