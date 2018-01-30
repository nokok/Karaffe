package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Name extends TermNode {

    public Name(final IdentifierToken token) {
        super(NodeType.NAME, token);
    }

    public Name(final String name) {
        this(new IdentifierToken.VarName(name));
    }

    @Override
    public void accept(final KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return this.getText();
    }
}
