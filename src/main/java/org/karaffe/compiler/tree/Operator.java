package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.types.InferResult;

public abstract class Operator extends TermNode {

    public static class Plus extends Operator {
        public Plus(final Token token) {
            super(NodeType.OP_PLUS, token);
        }

        @Override
        public void accept(final KaraffeTreeVisitor visitor) {
            visitor.visit(this);
        }

        @Override
        public Optional<InferResult> tryTypeInference(TypeInferenceContext context) {
            return Optional.of(InferResult.anyTarget(new Name("plus")));
        }
    }

    public Operator(final NodeType nodeType, final Token token) {
        super(nodeType, token);
    }

}
