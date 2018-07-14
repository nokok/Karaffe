package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.mir.util.attr.Attribute;

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
