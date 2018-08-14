package org.karaffe.compiler.base.mir.util;

import java.util.Objects;

public class Label {
    private Label superName;
    private String name;

    public Label(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Label(Label parentLabel, String name) {
        this.superName = Objects.requireNonNull(parentLabel);
        String parentName = parentLabel == null ? "#" : parentLabel.getName();
        if (!parentName.endsWith("#")) {
            parentName += "#";
        }
        if (Objects.requireNonNull(name).isEmpty()) {
            throw new IllegalArgumentException("Empty label name");
        }
        this.name = parentName + name;
    }

    public Label getSuperName() {
        return this.superName;
    }

    public String getName() {
        return name;
    }

    public String getSimpleName() {
        String[] names = this.name.split("#");
        return names[names.length - 1];
    }

    public static Label createRootLabel() {
        return new Label("#");
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

    @Override
    public String toString() {
        return name;
    }
}
