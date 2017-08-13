package org.karaffe.compiler.tree;

import java.util.List;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.NodeD;

public class MethodDecl implements NodeD {

    private final List<Token> modifierTokens;
    private final Token name;
    private final List<NodeD> formalList;
    private final Token returnType;
    private final List<NodeD> localVarDecl;
    private final List<NodeD> statements;

    public MethodDecl(final List<Token> modifierTokens, final Token name, final List<NodeD> formalList, final Token returnType, final List<NodeD> localVarDecl, final List<NodeD> statements) {
        this.modifierTokens = modifierTokens;
        this.name = name;
        this.formalList = formalList;
        this.returnType = returnType;
        this.localVarDecl = localVarDecl;
        this.statements = statements;
    }

    public List<Token> getModifierTokens() {
        return this.modifierTokens;
    }

    public Token getName() {
        return this.name;
    }

    public List<NodeD> getFormalList() {
        return this.formalList;
    }

    public Token getReturnType() {
        return this.returnType;
    }

    public List<NodeD> getLocalVarDecl() {
        return this.localVarDecl;
    }

    public List<NodeD> getStatements() {
        return this.statements;
    }

}
