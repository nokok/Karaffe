package org.karaffe.compiler.tree.v2.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class FullyQualifiedName extends AbstractTree {
    private final List<? extends SimpleName> names;

    public FullyQualifiedName(String... names) {
        this.names = Stream.of(names).map(SimpleName::new).collect(Collectors.toList());
    }

    public FullyQualifiedName(Position position, String... names) {
        super(position);
        this.names = Stream.of(names).map(SimpleName::new).collect(Collectors.toList());
    }

    public FullyQualifiedName(FullyQualifiedName other) {
        super(other.getPosition());
        this.names = new ArrayList<>(other.names);
    }

    public FullyQualifiedName(List<? extends SimpleName> names) {
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedName(SimpleName... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public FullyQualifiedName(Position position, List<? extends SimpleName> names) {
        super(position);
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedName(Position position, SimpleName... names) {
        this(position, new ArrayList<>(Arrays.asList(names)));
    }

    public SimpleName last() {
        return this.names.get(this.names.size() - 1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.names);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof FullyQualifiedName) {
            FullyQualifiedName other = (FullyQualifiedName) obj;
            return this.names.equals(other.names);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.join(".", this.names);
    }

}
