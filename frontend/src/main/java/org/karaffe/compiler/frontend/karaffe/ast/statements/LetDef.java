package org.karaffe.compiler.frontend.karaffe.ast.statements;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.Expression;
import org.karaffe.compiler.frontend.karaffe.ast.api.NameRef;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.ExpressionName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.Objects;
import java.util.Optional;

public abstract class LetDef extends AbstractTree implements Statement, NameRef {

    private final SimpleName name;
    private final TypeName typeName;
    private Expression initializer;

    public LetDef(SimpleName fieldName, Expression initializer) {
        this(Position.noPos(), fieldName, null, initializer);
    }

    public LetDef(SimpleName fieldName, TypeName typeName) {
        this(Position.noPos(), fieldName, typeName, null);
    }

    public LetDef(SimpleName fieldName, TypeName typeName, Expression initializer) {
        this(Position.noPos(), fieldName, typeName, initializer);
    }

    public LetDef(Position position, SimpleName fieldName, Expression initializer) {
        this(position, fieldName, null, initializer);
    }

    public LetDef(Position position, SimpleName fieldName, TypeName typeName) {
        this(position, fieldName, typeName, null);
    }

    public LetDef(Position position, SimpleName fieldName, TypeName typeName, Expression initializer) {
        super(position);
        this.name = Objects.requireNonNull(fieldName);
        this.typeName = typeName;
        this.initializer = initializer;
        if (this.typeName == null && this.initializer == null) {
            throw new IllegalArgumentException("this.typeName == null && this.initializer == null");
        }
    }

    public LetDef(LetDef other) {
        super(other.getPosition());
        this.name = other.name;
        this.typeName = other.getTypeName().orElse(null);
        this.initializer = other.getInitializer().orElse(null);
    }

    public SimpleName getName() {
        return this.name;
    }

    public boolean hasTypeName() {
        return this.getTypeName().isPresent();
    }

    public Optional<TypeName> getTypeName() {
        return Optional.ofNullable(this.typeName);
    }

    public Optional<Expression> getInitializer() {
        return Optional.ofNullable(this.initializer);
    }

    public boolean hasInitializer() {
        return this.getInitializer().isPresent();
    }

    public void replaceInitializer(Expression expression) {
        this.initializer = expression;
    }

    @Override
    public boolean isNormalizable() {
        if (this.initializer == null) {
            return false;
        }
        return this.initializer.isNormalizable();
    }

    @Override
    public Optional<ExpressionName> asExprName() {
        return Optional.of(new ExpressionName(this.getName().toString()));
    }

    @Override
    public String toString() {
        String typeNameString = Optional.ofNullable(this.typeName).map(TypeName::toString).orElse("");
        return String.format("let %s%s%s",
                this.name,
                typeNameString.isEmpty() ? "" : " : " + typeNameString,
                Optional.ofNullable(this.initializer).map(e -> " = " + e).orElse(""));
    }

}
