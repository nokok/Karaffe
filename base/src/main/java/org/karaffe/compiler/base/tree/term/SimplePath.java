package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class SimplePath extends AbstractPath {

    private boolean isPrimitive;
    private NameKind nameKind;

    protected SimplePath(String name, NameKind nameKind) {
        this(name, nameKind, false);
    }

    protected SimplePath(String name, NameKind nameKind, boolean isPrimitive) {
        super(name);
        this.nameKind = Objects.requireNonNull(nameKind);
        this.isPrimitive = isPrimitive;
    }

    @Override
    public NameKind getNameKind() {
        return this.nameKind;
    }

    @Override
    public String delimiterRegex() {
        return "$^";
    }

    @Override
    public boolean isPrimitiveType() {
        return this.isPrimitive;
    }

    @Override
    public boolean isDefaultInternalName() {
        return false;
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        switch (this.nameKind) {
        case VARNAME:
            return visitor.visitVarName(this, p);
        case THIS:
            return visitor.visitThisName(this, p);
        case TYPENAME:
            return visitor.visitTypeName(this, p);
        default:
            throw new IllegalStateException(this.nameKind.name());
        }
    }

}
