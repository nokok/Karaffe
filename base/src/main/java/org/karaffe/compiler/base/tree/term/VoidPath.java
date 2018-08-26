package org.karaffe.compiler.base.tree.term;

public class VoidPath extends AbstractPath {

    protected VoidPath() {
        super("void", "");
    }

    @Override
    public NameKind getNameKind() {
        return NameKind.VOID;
    }

    @Override
    public boolean isVoidType() {
        return true;
    }

    @Override
    public boolean isPrimitiveType() {
        return true;
    }

    @Override
    public boolean isDefaultInternalName() {
        return false;
    }

    @Override
    public String toString() {
        return "void";
    }
}
