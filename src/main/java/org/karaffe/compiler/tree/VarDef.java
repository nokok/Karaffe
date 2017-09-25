package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;

public class VarDef extends AbstractNode {

    private final Name name;
    private final TypeName type;

    public VarDef(final Name name, final TypeName type) {
        super(NodeType.DEFVAR);
        this.name = name;
        this.type = type;
    }

    public String getVarName() {
        return this.name.getText();
    }

    public String getTypeName() {
        return this.type.getText();
    }

}
