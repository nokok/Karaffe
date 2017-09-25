package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.ModifierToken;
import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;

public class ValDef extends AbstractNode {

    private final Modifiers modifiers;
    private final Name name;
    private final TypeName type;

    public ValDef(final Modifiers modifiers, final Name name, final TypeName type) {
        super(NodeType.DEFVAL);
        this.modifiers = modifiers;
        this.name = name;
        this.type = type;
    }

    public boolean has(final Class<? extends ModifierToken> modifier) {
        return this.modifiers.stream().filter(t -> t.is(modifier)).count() != 0;
    }

    public String getVarName() {
        return this.name.getText();
    }

    public String getTypeName() {
        return this.type.getText();
    }

}
