package org.karaffe.compiler.mir;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.mir.util.attr.Attribute;

import java.util.List;

public interface Instruction {
    InstructionType getInstType();

    void setInstType(InstructionType type);

    Position getPosition();

    void setPosition(Position position);

    void addAttribute(Attribute attribute);

    void setAttributes(List<Attribute> attributes);

    List<Attribute> getAttributes();

    default boolean hasAttribute() {
        return !getAttributes().isEmpty();
    }
}
