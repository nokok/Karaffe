package org.karaffe.compiler.tree;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.KNormalizable;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public abstract class Literal extends TermNode implements KNormalizable {

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

    abstract public boolean isBoolLiteral();

    abstract public boolean isTrueLiteral();

    public boolean isFalseLiteral() {
        if (this.isBoolLiteral()) {
            return !this.isTrueLiteral();
        }
        return false;
    }

    public abstract static class BoolLiteral extends Literal {
        public BoolLiteral(Token token) {
            super(token);
        }

        @Override
        public boolean isBoolLiteral() {
            return true;
        }
    }

    public static class TrueLiteral extends BoolLiteral {
        public TrueLiteral(Token token) {
            super(token);
        }

        @Override
        public boolean isTrueLiteral() {
            return true;
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class FalseLiteral extends BoolLiteral {

        public FalseLiteral(Token token) {
            super(token);
        }

        @Override
        public boolean isTrueLiteral() {
            return false;
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class IntLiteral extends Literal {
        public IntLiteral(Token token) {
            super(token);
        }

        @Override
        public boolean isBoolLiteral() {
            return false;
        }

        @Override
        public boolean isTrueLiteral() {
            return false;
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class ThisLiteral extends Literal {
        public ThisLiteral(Token token) {
            super(token);
        }

        @Override
        public boolean isBoolLiteral() {
            return false;
        }

        @Override
        public boolean isTrueLiteral() {
            return false;
        }

        @Override
        public void accept(KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }
    }
}
