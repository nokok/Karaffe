package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;
import org.karaffe.compiler.types.TypeResolver;

import karaffe.core.Bool;
import karaffe.core.Int;

public abstract class Literal extends TermNode {

    public abstract static class BoolLiteral extends Literal {
        public BoolLiteral(final Token token) {
            super(token);
        }

        @Override
        public boolean isBoolLiteral() {
            return true;
        }

        @Override
        public Optional<InferResult> tryTypeInference(final TypeContext context) {
            Optional<List<String>> findAllCompatibleClasses = TypeResolver.findAllCompatibleClasses(Bool.class);
            return findAllCompatibleClasses.map(InferResult::of);
        }
    }

    public static class FalseLiteral extends BoolLiteral {

        public FalseLiteral(final Token token) {
            super(token);
        }

        @Override
        public boolean isTrueLiteral() {
            return false;
        }
    }

    public static class IntLiteral extends Literal {
        public IntLiteral(final Token token) {
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
        public Optional<InferResult> tryTypeInference(final TypeContext context) {
            Optional<List<String>> findAllCompatibleClasses = TypeResolver.findAllCompatibleClasses(Int.class);
            return findAllCompatibleClasses.map(InferResult::of);
        }
    }

    public static class ThisLiteral extends Literal {
        public ThisLiteral(final Token token) {
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
    }

    public static class TrueLiteral extends BoolLiteral {
        public TrueLiteral(final Token token) {
            super(token);
        }

        @Override
        public boolean isTrueLiteral() {
            return true;
        }
    }

    public Literal(final Token token) {
        super(NodeType.LITERAL, token);
    }

    abstract public boolean isBoolLiteral();

    public boolean isFalseLiteral() {
        if (this.isBoolLiteral()) {
            return !this.isTrueLiteral();
        }
        return false;
    }

    abstract public boolean isTrueLiteral();

    @Override
    public NodeList normalize(final NormalizeContext context) {
        final List<Node> nodes = new ArrayList<>();
        final Name name = context.nextName(nodes);
        nodes.add(new Assign(name, this));
        return new NodeList(nodes);
    }

}
