package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.tree.TreeVisitor;

public class NestedPath extends AbstractPath {

    private Path basePath;
    private Path childPath;

    public NestedPath(Path basePath) {
        this(basePath, null);
    }

    public NestedPath(Path basePath, Path childPath) {
        super(basePath);
        this.basePath = basePath;
        this.childPath = childPath;
    }

    @Override
    public String asSimpleName() {
        return this.basePath.asSimpleName() + "[" + childPath.asSimpleName() + "]";
    }

    @Override
    public String asFullName() {
        return this.basePath.asFullName() + "[" + childPath.asFullName() + "]";
    }

    public Path getChild() {
        return this.childPath;
    }

    @Override
    public NameKind getNameKind() {
        return NameKind.NESTED;
    }

    @Override
    public String delimiterRegex() {
        return basePath.delimiterRegex();
    }

    @Override
    public void markResolved() {
        this.basePath.markResolved();
        this.childPath.markResolved();
    }

    @Override
    public void markUnResolved() {
        this.basePath.markUnResolved();
        this.childPath.markUnResolved();
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isDefaultInternalName() {
        return false;
    }

    @Override
    public <P> Path accept(TreeVisitor<?, P> visitor, P p) {
        return visitor.visitNestedName(this, p);
    }

    @Override
    public String toString() {
        return "[" + this.childPath + "]";
    }
}
