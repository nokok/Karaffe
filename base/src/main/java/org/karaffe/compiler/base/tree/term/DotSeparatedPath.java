package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.TreeVisitor;

import java.util.Objects;

public class DotSeparatedPath extends AbstractPath {

    private NameKind kind;

    public DotSeparatedPath(String name, NameKind nameKind) {
        super(name);
        this.kind = Objects.requireNonNull(nameKind);
    }

    @Override
    public NameKind getNameKind() {
        return this.kind;
    }

    @Override
    public String delimiterRegex() {
        return "\\.";
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isDefaultInternalName() {
        return this.name.equals("<root>");
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        switch (this.kind) {
        case TYPENAME:
            return visitor.visitTypeName(this, p);
        case MODULE:
            return visitor.visitModuleName(this, p);
        case PACKAGE:
            return visitor.visitPackageName(this, p);
        default:
            throw new IllegalStateException(this.kind.name());
        }
    }

}
