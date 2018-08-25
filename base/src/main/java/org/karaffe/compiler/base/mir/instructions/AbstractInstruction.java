package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.attr.Attribute;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractInstruction implements DeprecatedInstruction {
    private List<Attribute> attributes = new ArrayList<>();
    private Tree tree;
    private Position position = Position.noPos();

    @Override
    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public void addAttribute(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = Objects.requireNonNull(position);
    }

    @Override
    public Tree getTree() {
        return tree;
    }

    @Override
    public void setTree(Tree tree) {
        this.tree = Objects.requireNonNull(tree);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractInstruction that = (AbstractInstruction) o;
        return Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes);
    }
}
