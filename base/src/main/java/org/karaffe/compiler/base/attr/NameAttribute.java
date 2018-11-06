package org.karaffe.compiler.base.attr;

import java.util.Objects;

public class NameAttribute extends Attribute {
    private String name;

    public NameAttribute(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}
