package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;

public class MethodDef extends AbstractNode {

    public MethodDef(final Modifiers modifiers, final VarName name, final Parameters parameters, final TypeName returnType, final Block block) {
        super(NodeType.DEFMETHOD, new ArrayList<>(Arrays.asList(modifiers, name, parameters, returnType, block)));
    }

    public Modifiers getModifiers() {
        final Modifiers modifiers = (Modifiers) super.getChildren().get(0);
        return modifiers;
    }

    public boolean hasModifier(final Class<? extends ModifierToken> clazz) {
        return this.getModifiers().stream().filter(c -> c.is(clazz)).count() == this.getModifiers().size();
    }

    public String getMethodName() {
        return ((VarName) super.getChildren().get(1)).getText();
    }

    public Parameters getParameters() {
        return (Parameters) this.getChildren().get(2);
    }

    public String getReturnName() {
        return ((TypeName) super.getChildren().get(3)).getText();
    }

}
