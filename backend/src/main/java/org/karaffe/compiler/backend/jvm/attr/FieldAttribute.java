package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.mir.util.attr.Attribute;

import java.lang.reflect.Field;
import java.util.Objects;

public class FieldAttribute extends Attribute {
    private Field field;

    public FieldAttribute(Field field) {
        this.field = Objects.requireNonNull(field);
    }

    public Field getFieldObject() {
        return field;
    }
}
