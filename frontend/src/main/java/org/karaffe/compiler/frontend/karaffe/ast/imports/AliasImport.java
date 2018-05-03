package org.karaffe.compiler.frontend.karaffe.ast.imports;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.Objects;

public class AliasImport extends AbstractTree implements ImportStatement {
    private final FullyQualifiedTypeName before;
    private final TypeName after;

    public AliasImport(FullyQualifiedTypeName before, TypeName typeName) {
        this.before = Objects.requireNonNull(before);
        this.after = Objects.requireNonNull(typeName);
    }

    public AliasImport(Position position, FullyQualifiedTypeName before, TypeName typeName) {
        super(position);
        this.before = Objects.requireNonNull(before);
        this.after = Objects.requireNonNull(typeName);
    }

    public AliasImport(AliasImport other) {
        this(other.getPosition(), other.getBefore(), other.getAfter());
    }

    public FullyQualifiedTypeName getBefore() {
        return this.before;
    }

    public TypeName getAfter() {
        return this.after;
    }

    @Override
    public String toString() {
        return String.format("alias %s -> %s", this.getBefore(), this.getAfter());
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.ALIAS_IMPORT;
    }

    @Override
    public SimpleName getImportedSimpleName() {
        return this.after.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof AliasImport) {
            AliasImport other = (AliasImport) obj;
            return this.before.equals(other.before) && this.after.equals(other.after);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.before, this.after);
    }

}
