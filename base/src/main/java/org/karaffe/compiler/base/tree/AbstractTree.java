package org.karaffe.compiler.base.tree;

import org.karaffe.compiler.base.attr.Attribute;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.term.EmptyTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractTree implements Tree {
    private Position position;
    private Tree name;
    private Tree typeName;
    private List<Attribute> attributes;
    private List<Tree> modifiers;
    private List<Tree> children;

    public AbstractTree() {
        this.position = Position.noPos();
        this.name = null; // new EmptyTreeしたかったが構造上StackOverflowになるので
        this.typeName = null;
        this.attributes = new ArrayList<>();
        this.modifiers = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    @Override
    public void addAttribute(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
    }

    @Override
    public Tree getTypeName() {
        return Optional.ofNullable(this.typeName).orElse(new EmptyTree());
    }

    @Override
    public void setTypeName(Tree type) {
        this.typeName = Objects.requireNonNull(type);
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
    public Tree getName() {
        return Optional.ofNullable(this.name).orElse(new EmptyTree());
    }

    @Override
    public void setName(Tree name) {
        this.name = Objects.requireNonNull(name);
    }

}
