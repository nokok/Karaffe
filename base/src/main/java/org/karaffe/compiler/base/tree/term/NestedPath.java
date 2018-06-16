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
}
