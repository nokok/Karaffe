package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.modifiers.Modifier;
import org.karaffe.compiler.base.tree.term.Name;
import org.karaffe.compiler.base.tree.term.Terms;
import org.karaffe.compiler.base.tree.type.Type;
import org.karaffe.compiler.base.tree.type.Types;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractTree implements Tree, ModifiableElement, NamedElement {
    private Tree parent;
    private TreeKind kind;
    private Position position;
    private Name name;
    private Type type;
    private Set<Modifier> modifiers;
    private List<Tree> children;

    public AbstractTree(TreeKind treeKind) {
        this(null, treeKind);
    }

    public AbstractTree(Tree parent, TreeKind treeKind) {
        this.parent = parent;
        this.kind = Objects.requireNonNull(treeKind);
        this.position = Position.noPos();
        this.name = Terms.emptyName();
        this.type = Types.noType();
        this.modifiers = new HashSet<>();
        this.children = new ArrayList<>();
    }

    public Type asType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = Objects.requireNonNull(type);
    }

    public TreeKind getKind() {
        return kind;
    }

    public void setKind(TreeKind kind) {
        this.kind = Objects.requireNonNull(kind);
    }

    protected void addChild(Tree child) {
        this.children.add(Objects.requireNonNull(child));
    }

    protected void setOrReplaceChild(int index, Tree child) {
        this.children.set(index, child);
    }

    protected List<Tree> getChildren() {
        return this.children;
    }

    public void setPos(Position pos) {
        this.position = Objects.requireNonNull(pos);
    }

    public Position getPos() {
        return this.position;
    }

    @Override
    public Set<Modifier> getModifiers() {
        return this.modifiers;
    }

    @Override
    public void addModifier(Modifier modifier) {
        this.modifiers.add(Objects.requireNonNull(modifier));
    }

    @Override
    public void clearModifiers() {
        this.modifiers.clear();
    }

    @Override
    public void setName(Name name) {
        this.name = Objects.requireNonNull(name);
    }

    @Override
    public Name getName() {
        return this.name;
    }
}
