package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public abstract class Operator extends TermNode {

    public Operator(final NodeType nodeType, final Token token) {
        super(nodeType, token);
    }

    public static class Plus extends Operator {
        public Plus(final Token token) {
            super(NodeType.OP_PLUS, token);
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }
}
