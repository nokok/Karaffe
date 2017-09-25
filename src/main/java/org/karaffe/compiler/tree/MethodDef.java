package org.karaffe.compiler.tree;

import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.Name;
import org.karaffe.compiler.tree.base.NodeType;

public class MethodDef extends AbstractNode {

    private final Modifiers modifiers;
    private final Name name;
    private final Parameters parameters;
    private final TypeName returnType;
    private final List<VarDef> localVarDecl;
    private final List<Statement> statements;

    public MethodDef(final Modifiers modifiers, final Name name, final Parameters parameters, final TypeName returnType, final List<VarDef> localVarDecl, final List<Statement> statements) {
        super(NodeType.DEFMETHOD);
        this.modifiers = modifiers;
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.localVarDecl = localVarDecl;
        this.statements = statements;
    }

}
