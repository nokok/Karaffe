package org.karaffe.compiler.tree.v2.imports;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.ImportStatement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.TreeVisitor;
import org.karaffe.compiler.tree.v2.names.FullyQualifiedName;

public class SimpleImport extends AbstractTree implements ImportStatement {
    private final FullyQualifiedName name;

    public SimpleImport(FullyQualifiedName name) {
        this.name = name;
    }

    public SimpleImport(Position position, FullyQualifiedName name) {
        super(position);
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("import %s;", this.name.toString());
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.SIMPLE_IMPORT_DEF;
    }

    @Override
    public void accept(TreeVisitor visitor) {
        visitor.visit(this);
    }
}
