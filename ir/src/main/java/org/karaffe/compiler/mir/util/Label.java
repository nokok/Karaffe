package org.karaffe.compiler.mir.util;

import java.util.Objects;

public class Label {
    private String name;

    public Label(String name) {
        this(null, name);
    }

    public Label(Label parentLabel, String name) {
        String parentName = "#";
        if (parentLabel == null) {
            parentName += parentLabel.getName();
        }
        this.name = parentName + Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }
}
