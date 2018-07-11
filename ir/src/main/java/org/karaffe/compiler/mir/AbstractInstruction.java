package org.karaffe.compiler.mir;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.mir.util.attr.Attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractInstruction implements Instruction {
    private List<Attribute> attributes = new ArrayList<>();
    private Position position;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = Objects.requireNonNull(attributes);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = Objects.requireNonNull(position);
    }
}
