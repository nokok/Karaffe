package org.karaffe.compiler.frontend.karaffe.ast.imports;

import java.util.Objects;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

public class SimpleImport extends AbstractTree implements ImportStatement {
    private final FullyQualifiedTypeName name;

    public SimpleImport(FullyQualifiedTypeName name) {
        this.name = name;
    }

    public SimpleImport(Position position, FullyQualifiedTypeName name) {
        super(position);
        this.name = name;
    }

    public SimpleImport(SimpleImport other) {
        this(other.getPosition(), other.getName());
    }

    public FullyQualifiedTypeName getName() {
        return this.name;
    }

    @Override
    public SimpleName getImportedSimpleName() {
        return this.name.last();
    }

    @Override
    public String toString() {
        return String.format("import %s;", this.name.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof SimpleImport) {
            SimpleImport other = (SimpleImport) obj;
            return this.name.equals(other.name);
        }
        return false;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.SIMPLE_IMPORT_DEF;
    }

}
