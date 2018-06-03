package org.karaffe.compiler.base.tree.term;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.TreeKind;
import org.karaffe.compiler.base.tree.TreeVisitor;
import org.karaffe.compiler.base.tree.modifier.Modifier;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.tree.type.Types;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

class SimpleName implements Name {

    static final SimpleName EMPTY_NAME = new SimpleName("__EMPTY__", NameType.NONE);
    private Position position;
    private Type type;
    private final NameType nameType;
    private final String name;

    SimpleName(String name, NameType nameType) {
        this.nameType = Objects.requireNonNull(nameType);
        this.name = Objects.requireNonNull(name);
        this.position = Position.noPos();
        this.type = Types.noType();
    }

    @Override
    public int length() {
        return name.length();
    }

    @Override
    public char charAt(int i) {
        return name.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return name.subSequence(i, i1);
    }

    @Override
    public <R, P> R accept(TreeVisitor<R, P> visitor, P p) {
        return visitor.visit(this, p);
    }

    @Override
    public TreeKind getKind() {
        return TreeKind.NAME;
    }

    @Override
    public void setKind(TreeKind kind) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NameType getType() {
        return nameType;
    }

    @Override
    public void setPos(Position pos) {
        this.position = Objects.requireNonNull(pos);
    }

    @Override
    public Position getPos() {
        return this.position;
    }

    @Override
    public Set<Modifier> getModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addModifier(Modifier modifier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setName(Name name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Name getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return Optional.ofNullable(this.name).orElse("__EMPTY__");
    }

    @Override
    public void addChild(Tree child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOrReplaceChild(int index, Tree child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tree> getChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type asType() {
        return this.type;
    }

    @Override
    public void setType(Type type) {
        this.type = Objects.requireNonNull(type);
    }

}
