package org.karaffe.compiler.tree.v2.statements;

import java.util.Objects;
import java.util.Optional;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public abstract class LetDef extends AbstractTree implements Statement {

    private final SimpleName fieldName;
    private final Optional<SimpleName> typeName;
    private Optional<Expression> initializer;

    public LetDef(SimpleName fieldName, Expression initializer) {
        this(Position.noPos(), fieldName, null, initializer);
    }

    public LetDef(SimpleName fieldName, SimpleName typeName) {
        this(Position.noPos(), fieldName, typeName, null);
    }

    public LetDef(SimpleName fieldName, SimpleName typeName, Expression initializer) {
        this(Position.noPos(), fieldName, typeName, initializer);
    }

    public LetDef(Position position, SimpleName fieldName, Expression initializer) {
        this(position, fieldName, null, initializer);
    }

    public LetDef(Position position, SimpleName fieldName, SimpleName typeName) {
        this(position, fieldName, typeName, null);
    }

    public LetDef(Position position, SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(position);
        this.fieldName = Objects.requireNonNull(fieldName);
        this.typeName = Optional.ofNullable(typeName);
        this.initializer = Optional.ofNullable(initializer);
        if (!this.typeName.isPresent() && !this.initializer.isPresent()) {
            throw new IllegalArgumentException("this.typeName.isEmpty() && this.initializer.isEmpty()");
        }
    }

    public LetDef(LetDef other) {
        super(other.getPosition());
        this.fieldName = other.fieldName;
        this.typeName = other.getTypeName();
        this.initializer = other.getInitializer();
        other.getAttributes().forEach(this::addAttribute);
    }

    public SimpleName getName() {
        return this.fieldName;
    }

    public boolean hasTypeName() {
        return this.getTypeName().isPresent();
    }

    public Optional<SimpleName> getTypeName() {
        return this.typeName;
    }

    public Optional<Expression> getInitializer() {
        return this.initializer;
    }

    public boolean hasInitializer() {
        return this.getInitializer().isPresent();
    }

    public void replaceInitializer(Expression expression) {
        this.initializer = Optional.ofNullable(expression);
    }

    @Override
    public String toString() {
        return String.format("let %s%s%s",
                this.fieldName,
                this.typeName.map(t -> " : " + t).orElse(""),
                this.initializer.map(e -> " = " + e).orElse(""));
    }
}
