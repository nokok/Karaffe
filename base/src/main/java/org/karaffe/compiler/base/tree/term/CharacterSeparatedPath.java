package org.karaffe.compiler.base.tree.term;

import java.util.Objects;

public class CharacterSeparatedPath extends AbstractPath {

    protected NameKind nameKind;

    public CharacterSeparatedPath(NameKind nameKind, String name, String delimiterRegex) {
        super(name, delimiterRegex);
        this.nameKind = Objects.requireNonNull(nameKind);
    }

    @Override
    public NameKind getNameKind() {
        return this.nameKind;
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isDefaultInternalName() {
        return false;
    }

}
