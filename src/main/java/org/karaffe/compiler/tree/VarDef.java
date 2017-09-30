package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;

public class VarDef extends AbstractNode {

    public VarDef(final Modifiers modifiers, final VarName name, final TypeName type) {
        super(NodeType.DEFVAR, new ArrayList<>(Arrays.asList(modifiers, name, type)));
    }

    public boolean has(final Class<? extends ModifierToken> modifier) {
        return ((Modifiers) this.getChildren().get(0)).stream().filter(t -> t.is(modifier)).count() != 0;
    }

    public String getVarName() {
        return ((VarName) this.getChildren().get(1)).getText();
    }

    public String getTypeName() {
        return ((TypeName) this.getChildren().get(2)).getText();
    }

}
