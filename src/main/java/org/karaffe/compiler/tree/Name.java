package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;
import org.karaffe.compiler.types.InferResult;

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
        if (this.getText().equals("+")) {
            return "plus";
        }
        return this.getText();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Name) {
            Name that = (Name) obj;
            if (super.getToken().getPosition().equals(that.getToken().getPosition()) && this.getName().equals(that.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeInferenceContext context) {
        if (!context.hasAlreadyDefinedName(this)) {
            throw new IllegalStateException();
        }
        return context.getInferredType(this);
    }
}
