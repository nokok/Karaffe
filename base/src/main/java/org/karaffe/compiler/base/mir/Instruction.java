package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.mir.util.attr.Attribute;
import org.karaffe.compiler.base.pos.Position;

import java.util.List;

public interface Instruction {
    InstructionType getInstType();

    Position getPosition();

    void setPosition(Position position);

    void addAttribute(Attribute attribute);

    void setAttributes(List<Attribute> attributes);

    List<Attribute> getAttributes();

    default boolean hasAttribute() {
        return !getAttributes().isEmpty();
    }
}
