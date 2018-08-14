package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.attr.Attribute;

import java.lang.reflect.Field;
import java.util.Objects;

public class FieldClassAttribute extends Attribute {
    private Field field;

    public FieldClassAttribute(Field field) {
        this.field = Objects.requireNonNull(field);
    }

    public Field getFieldObject() {
        return field;
    }
}
