package org.karaffe.compiler.frontend.karaffe.ast.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.karaffe.compiler.base.pos.Position;

public class FullyQualifiedTypeName extends TypeName {

    private final List<SimpleName> prefixNames;

    public FullyQualifiedTypeName(Class<?> clazz) {
        this(clazz, new ArrayList<>(0));
    }

    public FullyQualifiedTypeName(Class<?> clazz, List<? extends FullyQualifiedTypeName> parameterizedTypes) {
        this(Position.noPos(), Arrays.asList(clazz.getCanonicalName().split("\\.")).stream().map(SimpleName::new).collect(Collectors.toList()), parameterizedTypes);
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("primitive type not allowed");
        }
    }

    public FullyQualifiedTypeName(String... names) {
        this(Position.noPos(), names);
    }

    public FullyQualifiedTypeName(Position position, String... names) {
        this(position, Stream.of(names).map(SimpleName::new).collect(Collectors.toList()));
    }

    public FullyQualifiedTypeName(FullyQualifiedTypeName other) {
        super(other.getPosition(), other.last(), other.getParameterizedTypes());
        this.prefixNames = new ArrayList<>(other.prefixNames);
    }

    public FullyQualifiedTypeName(List<? extends SimpleName> names) {
        this(Position.noPos(), names);
    }

    public FullyQualifiedTypeName(SimpleName... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public FullyQualifiedTypeName(Position position, List<? extends SimpleName> names) {
        this(position, names, new ArrayList<>(0));
    }

    public FullyQualifiedTypeName(Position position, List<? extends SimpleName> names, List<? extends FullyQualifiedTypeName> parameterizedTypes) {
        super(position, names.get(names.size() - 1), parameterizedTypes);
        this.prefixNames = new ArrayList<>(names.subList(0, names.size() - 1));
    }

    public FullyQualifiedTypeName(Position position, SimpleName... names) {
        this(position, new ArrayList<>(Arrays.asList(names)));
    }

    public String getFullName() {
        return getFullName(".");
    }

    public String getFullName(String delimiter) {
        List<SimpleName> names = new ArrayList<>(this.prefixNames);
        names.add(this.last());
        return String.join(delimiter, names);
    }

    public void setArrayType() {
        this.isArray = true;
    }

    public void unsetArrayType() {
        this.isArray = false;
    }

    public SimpleName last() {
        return super.getName();
    }

    @Override
    public boolean isFullyQualified() {
        return true;
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
        List<SimpleName> s = new ArrayList<>(this.prefixNames);
        s.add(super.getName());
        return String.join(".", s) + (this.isArrayType() ? "[]" : "");
    }

}
