package org.karaffe.compiler.tree;

import java.util.Optional;

import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.lexer.IdentifierToken;
import org.karaffe.compiler.types.InferResult;

public class Name extends TermNode {

    public Name(final IdentifierToken token) {
        super(NodeType.NAME, token);
    }

    public Name(final String name) {
        this(new IdentifierToken.VarName(name));
    }

    public String getName() {
        return getName(new DefaultNameMapper());
    }

    public String getName(NameMapper mapper) {
        return mapper.convert(this);
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
    public Optional<InferResult> tryTypeInference(TypeContext context) {
        if (!context.hasAlreadyDefinedName(this)) {
            throw new IllegalStateException();
        }
        return context.getInferredType(this);
    }
}
