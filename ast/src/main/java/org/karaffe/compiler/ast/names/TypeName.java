package org.karaffe.compiler.ast.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.pos.Position;

public class TypeName extends SimpleName {
    private final SimpleName name;
    private final List<TypeName> parameterizedTypes;

    public TypeName(String name) {
        this(new SimpleName(name), new ArrayList<>(0));
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
        super(name);
        this.name = name;
        this.parameterizedTypes = new ArrayList<>(parameterizedType);
        if (this.name.toString().contains(".")) {
            throw new IllegalArgumentException();
        }
    }

    public TypeName(Position position, SimpleName name, List<? extends TypeName> parameterizedType) {
        super(position, name);
        this.name = name;
        this.parameterizedTypes = new ArrayList<>(parameterizedType);
    }

    public TypeName(TypeName otherTypeName) {
        super(otherTypeName.getPosition(), otherTypeName);
        this.name = new SimpleName(otherTypeName.name);
        this.parameterizedTypes = new ArrayList<>(otherTypeName.parameterizedTypes);
    }

    public boolean isVoidType() {
        return this.name.toString().equals("void");
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

    public static TypeName voidType() {
        return new TypeName(new SimpleName("void"));
    }

    @Override
    public String toString() {
        if (this.parameterizedTypes.isEmpty()) {
            return this.name.toString();
        }
        return String.format("%s[%s]",
                this.name,
                String.join(", ", this.parameterizedTypes.stream().map(TypeName::toString).collect(Collectors.toList())));
    }

    public static TypeName rootClass() {
        return new TypeName(SimpleName.rootClass());
    }

}
