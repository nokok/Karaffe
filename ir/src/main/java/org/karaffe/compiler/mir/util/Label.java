package org.karaffe.compiler.mir.util;

import java.util.Objects;

public class Label {
    private String name;

    public Label(String name) {
        this(null, name);
    }

    public Label(Label parentLabel, String name) {
        String parentName = "#";
        if (parentLabel != null) {
            parentName += parentLabel.getName();
        }
        this.name = parentName + Objects.requireNonNull(name);
    }

    public String getName() {
        return name;
    }

    public static Label createRootLabel() {
        return new Label("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(name, label.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
