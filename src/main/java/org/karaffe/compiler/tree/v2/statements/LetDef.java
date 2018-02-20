package org.karaffe.compiler.tree.v2.statements;

import java.util.Optional;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.Expression;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public abstract class LetDef extends AbstractTree {

    private final SimpleName fieldName;
    private final Optional<SimpleName> typeName;
    private final Optional<Expression> initializer;

    public LetDef(SimpleName fieldName, Expression initializer) {
        this(Position.noPos(), fieldName, null, initializer);
    }

    public LetDef(SimpleName fieldName, SimpleName typeName) {
        this(Position.noPos(), fieldName, typeName, null);
    }

    public LetDef(SimpleName fieldName, SimpleName typeName, Expression initializer) {
        this(Position.noPos(), fieldName, typeName, initializer);
    }

    public LetDef(Position position, SimpleName fieldName, SimpleName typeName, Expression initializer) {
        super(position);
        this.fieldName = fieldName;
        this.typeName = Optional.ofNullable(typeName);
        this.initializer = Optional.ofNullable(initializer);
        if (!this.typeName.isPresent() && !this.initializer.isPresent()) {
            throw new IllegalArgumentException("this.typeName.isEmpty() && this.initializer.isEmpty()");
        }
    }

    @Override
    public String toString() {
        return String.format("let %s%s%s",
                this.fieldName,
                this.typeName.map(t -> " : " + t).orElse(""),
                this.initializer.map(e -> " = " + e).orElse(""));
    }
}
