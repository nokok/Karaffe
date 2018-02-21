package org.karaffe.compiler.tree.v2.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.pos.Position;

public abstract class AbstractTree implements Tree, TreePosition {
    private final Position treePosition;
    private final List<Attribute> attributes;

    public AbstractTree() {
        this(Position.noPos());
    }

    public AbstractTree(Position position) {
        this.treePosition = Objects.requireNonNull(position);
        this.attributes = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    @Override
    public List<? extends Attribute> getAttributes() {
        return new ArrayList<>(this.attributes);
    }

    @Override
    public Position getPosition() {
        return this.treePosition;
    }

}
