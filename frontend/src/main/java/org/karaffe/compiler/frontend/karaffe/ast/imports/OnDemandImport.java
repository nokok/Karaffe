package org.karaffe.compiler.frontend.karaffe.ast.imports;

import java.util.Objects;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.ImportStatement;
import org.karaffe.compiler.frontend.karaffe.ast.api.StatementType;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

public class OnDemandImport extends AbstractTree implements ImportStatement {

    private final PackageName packageName;

    public OnDemandImport(PackageName packageName) {
        this(Position.noPos(), packageName);
    }

    public OnDemandImport(Position position, PackageName packageName) {
        super(position);
        this.packageName = Objects.requireNonNull(packageName);
    }

    public OnDemandImport(OnDemandImport other) {
        this(other.getPosition(), other.getPackageName());
    }

    @Override
    public SimpleName getImportedSimpleName() {
        return SimpleName.wildCard();
    }

    public PackageName getPackageName() {
        return this.packageName;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.ONDEMAND_IMPORT_DEF;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof OnDemandImport) {
            OnDemandImport other = (OnDemandImport) obj;
            return this.packageName.equals(other.packageName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.packageName);
    }

    @Override
    public String toString() {
        return "import " + this.packageName + "._;";
    }

}
