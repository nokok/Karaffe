package net.nokok.karaffe.javacc;

import static java.util.Objects.requireNonNull;

public class Name {

    private final String name;

    public Name(String name) {
        this.name = requireNonNull(name);
    }

    public boolean startsWith(String s) {
        return name.startsWith(s);
    }

    @Override
    public String toString() {
        return name;
    }
}
