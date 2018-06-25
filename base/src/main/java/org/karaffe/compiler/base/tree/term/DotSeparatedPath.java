package org.karaffe.compiler.base.tree.term;

public class DotSeparatedPath extends CharacterSeparatedPath {

    public DotSeparatedPath(String name, NameKind nameKind) {
        super(nameKind, name, "\\.");
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isDefaultInternalName() {
        return this.name.equals("<root>");
    }

}
