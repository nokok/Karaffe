package org.karaffe.compiler.mir;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.mir.util.attr.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractInstruction implements Instruction {
    private List<Attribute> attributes = new ArrayList<>();
    private InstructionType type;
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
    public InstructionType getInstType() {
        return this.type;
    }

    @Override
    public void setInstType(InstructionType type) {
        this.type = Objects.requireNonNull(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractInstruction that = (AbstractInstruction) o;
        return Objects.equals(attributes, that.attributes) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes, type);
    }
}
