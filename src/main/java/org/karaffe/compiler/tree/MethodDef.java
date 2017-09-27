package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;

import org.karaffe.compiler.lexer.ModifierToken;
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

    public boolean hasModifier(final Class<? extends ModifierToken> clazz) {
        return this.modifiers.stream().filter(c -> c.is(clazz)).count() == this.modifiers.size();
    }

    public String getMethodName() {
        return this.name.getText();
    }

    public String getReturnName() {
        return this.returnType.getText();
    }

    public Parameters getParameters() {
        return this.parameters;
    }

    public List<VarDef> localVarDefs() {
        return new ArrayList<>(this.localVarDecl);
    }

    public List<Statement> statements() {
        return new ArrayList<>(this.statements);
    }
}
