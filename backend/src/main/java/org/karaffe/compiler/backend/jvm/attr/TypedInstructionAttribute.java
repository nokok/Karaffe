package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.mir.util.attr.Attribute;

import java.util.Objects;

public class TypedInstructionAttribute extends Attribute {
    private Class<?> clazz;

    public TypedInstructionAttribute(Class<?> clazz) {
        this.clazz = Objects.requireNonNull(clazz);
    }

    public Class<?> getTypedInfo() {
        return this.clazz;
    }
}
