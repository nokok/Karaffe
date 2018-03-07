package org.karaffe.compiler.tree.v2.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class FullyQualifiedTypeName extends AbstractTree {

    private final List<SimpleName> names;

    public FullyQualifiedTypeName(Class<?> clazz) {
        this(clazz.getCanonicalName().split("\\."));
    }

    public FullyQualifiedTypeName(String... names) {
        this(Position.noPos(), names);
    }

    public FullyQualifiedTypeName(Position position, String... names) {
        super(position);
        this.names = Stream.of(names).map(SimpleName::new).collect(Collectors.toList());
    }

    public FullyQualifiedTypeName(FullyQualifiedTypeName other) {
        super(other.getPosition());
        this.names = new ArrayList<>(other.names);
    }

    public FullyQualifiedTypeName(List<? extends SimpleName> names) {
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedTypeName(SimpleName... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public FullyQualifiedTypeName(Position position, List<? extends SimpleName> names) {
        super(position);
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedTypeName(Position position, SimpleName... names) {
        this(position, new ArrayList<>(Arrays.asList(names)));
    }

    public String getFullName() {
        return getFullName(".");
    }

    public String getFullName(String delimiter) {
        return String.join(delimiter, this.names);
    }

    public SimpleName last() {
        return this.names.get(this.names.size() - 1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFullName());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof FullyQualifiedTypeName) {
            FullyQualifiedTypeName other = (FullyQualifiedTypeName) obj;
            return this.getFullName().equals(other.getFullName());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.join(".", this.names);
    }

}
