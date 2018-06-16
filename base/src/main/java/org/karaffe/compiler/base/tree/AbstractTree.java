package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.term.Path;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTree implements Tree {
    private Tree parent;
    private TreeKind kind;
    private Position position;
    private Path name;
    private Path typeName;
    private List<Tree> modifiers;
    private List<Tree> children;

    public AbstractTree(TreeKind treeKind) {
        this(null, treeKind);
    }

    public AbstractTree(Tree parent, TreeKind treeKind) {
        this.parent = parent;
        this.kind = Objects.requireNonNull(treeKind);
        this.position = Position.noPos();
        this.name = Terms.emptyName();
        this.typeName = Terms.emptyName();
        this.modifiers = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    @Override
    public Path getTypeName() {
        return this.typeName;
    }

    @Override
    public void setTypeName(Path type) {
        this.typeName = Objects.requireNonNull(type);
    }

    @Override
    public TreeKind getKind() {
        return kind;
    }

    @Override
    public void setKind(TreeKind kind) {
        this.kind = Objects.requireNonNull(kind);
    }

    @Override
    public Tree getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Tree tree) {
        this.parent = tree;
    }

    @Override
    public void addFirst(Tree child) {
        this.children.add(0, child);
    }

    @Override
    public void addChild(Tree child) {
        this.children.add(Objects.requireNonNull(child));
    }

    @Override
    public void setOrReplaceChild(int index, Tree child) {
        if (this.children.size() <= index) {
            int diff = index - this.children.size();
            for (int i = 0; i <= diff; i++) {
                this.children.add(null);
            }
        }
        this.children.set(index, child);
    }

    @Override
    public List<Tree> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<Tree> children) {
        this.children = Objects.requireNonNull(children);
    }

    @Override
    public Position getPos() {
        return this.position;
    }

    @Override
    public void setPos(Position pos) {
        this.position = Objects.requireNonNull(pos);
    }

    @Override
    public List<Tree> getModifiers() {
        return this.modifiers;
    }

    @Override
    public void setModifiers(List<Tree> modifiers) {
        this.modifiers = Objects.requireNonNull(modifiers);
    }

    @Override
    public void addModifier(Tree modifier) {
        this.modifiers.add(Objects.requireNonNull(modifier));
    }

    @Override
    public void clearModifiers() {
        this.modifiers.clear();
    }

    @Override
    public Path getName() {
        return this.name;
    }

    @Override
    public void setName(Path name) {
        this.name = Objects.requireNonNull(name);
    }
}
