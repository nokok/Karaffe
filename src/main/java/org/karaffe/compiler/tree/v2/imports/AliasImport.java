package org.karaffe.compiler.tree.v2.imports;

import java.util.Objects;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;

public class AliasImport extends AbstractTree implements ImportStatement {
    private final TypeName before;
    private final TypeName after;

    public AliasImport(TypeName before, TypeName typeName) {
        this.before = Objects.requireNonNull(before);
        this.after = Objects.requireNonNull(typeName);
    }

    public AliasImport(Position position, TypeName before, TypeName typeName) {
        super(position);
        this.before = Objects.requireNonNull(before);
        this.after = Objects.requireNonNull(typeName);
    }

    public TypeName getBefore() {
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
