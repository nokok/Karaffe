package org.karaffe.compiler.tree.attr;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Attributes {
    private final List<Attribute> attributes = new ArrayList<>();

    public void add(Attribute attribute) {
        this.attributes.add(Objects.requireNonNull(attribute));
    }

    public boolean isEmpty() {
        return this.attributes.isEmpty();
    }

    public Stream<Attribute> stream() {
        return this.attributes.stream();
    }

    @Override
    public String toString() {
        return this.attributes.toString();
    }
}
