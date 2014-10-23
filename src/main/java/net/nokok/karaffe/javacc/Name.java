package net.nokok.karaffe.javacc;

import static java.util.Objects.requireNonNull;

public class Name {

    private final String name;

    public Name(String name) {
        this.name = requireNonNull(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
