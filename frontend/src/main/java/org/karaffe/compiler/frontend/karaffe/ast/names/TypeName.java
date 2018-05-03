package org.karaffe.compiler.frontend.karaffe.ast.names;

import org.karaffe.compiler.base.pos.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TypeName extends SimpleName {
    private final SimpleName name;
    private final List<TypeName> parameterizedTypes;
    protected boolean isArray;

    public TypeName(String name) {
        this(new SimpleName(name), new ArrayList<>(0));
    }

    public TypeName(String name, boolean isArray) {
        this(new SimpleName(name), new ArrayList<>(0), isArray);
    }

    public TypeName(Position position, String name) {
        this(position, new SimpleName(name), new ArrayList<>(0));
    }

    public TypeName(SimpleName name) {
        this(name, new ArrayList<>(0));
    }

    public TypeName(Position position, SimpleName name) {
        this(position, name, new ArrayList<>(0));
    }

    public TypeName(SimpleName name, TypeName parameterizedType) {
        this(name, Arrays.asList(parameterizedType));
    }

    public TypeName(Position position, SimpleName name, TypeName parameterizedType) {
        this(position, name, Arrays.asList(parameterizedType));
    }

    public TypeName(SimpleName name, List<? extends TypeName> parameterizedType) {
        this(Position.noPos(), name, parameterizedType);
    }

    public TypeName(SimpleName name, List<? extends TypeName> parameterizedType, boolean isArray) {
        this(Position.noPos(), name, parameterizedType, isArray);
    }

    public TypeName(Position position, SimpleName name, List<? extends TypeName> parameterizedType) {
        this(position, name, parameterizedType, false);
    }

    public TypeName(Position position, SimpleName name, List<? extends TypeName> parameterizedType, boolean isArray) {
        super(position, name);
        this.name = name;
        if (this.name.toString().contains(".")) {
            throw new IllegalArgumentException();
        }
        this.parameterizedTypes = new ArrayList<>(parameterizedType);
        this.isArray = isArray;
    }

    public TypeName(TypeName otherTypeName) {
        super(otherTypeName.getPosition(), otherTypeName);
        this.name = new SimpleName(otherTypeName.name);
        this.parameterizedTypes = new ArrayList<>(otherTypeName.parameterizedTypes);
        this.isArray = otherTypeName.isArray;
    }

    public static TypeName voidType() {
        return new FullyQualifiedTypeName(new SimpleName("void"));
    }

    public static TypeName rootClass() {
        return new TypeName(SimpleName.rootClass());
    }

    public boolean isVoidType() {
        return this.name.toString().equals("void");
    }

    public boolean isArrayType() {
        return this.isArray;
    }

    public boolean isFullyQualified() {
        return false;
    }

    public SimpleName getName() {
        return this.name;
    }

    public List<? extends TypeName> getParameterizedTypes() {
        return new ArrayList<>(this.parameterizedTypes);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(this.name);
        if (!this.parameterizedTypes.isEmpty()) {
            b.append("[");
            b.append(String.join(", ", this.parameterizedTypes.stream().map(TypeName::toString).collect(Collectors.toList())));
            b.append("]");
        }
        if (this.isArray) {
            b.append("[]");
        }
        return b.toString();
    }

}
