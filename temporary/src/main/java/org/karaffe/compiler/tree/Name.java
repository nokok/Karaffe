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

    public boolean isConstructorAccess() {
        return this.getText().equals("<init>");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Name) {
            Name that = (Name) obj;
            return this.getName().equals(that.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public Optional<InferResult> tryTypeInference(TypeContext context) {
        if (context.hasAlreadyDefinedName(this)) {
            return context.getInferredType(this);
        }
        return Optional.of(InferResult.noHint());
    }
}
