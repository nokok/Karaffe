package org.karaffe.compiler.ast.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.AbstractTree;
import org.karaffe.compiler.ast.api.TypeDefMember;
import org.karaffe.compiler.ast.api.TypeDefStatement;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

public abstract class AbstractTypeDefStatement extends AbstractTree implements TypeDefStatement {
    private final SimpleName typeName;
    private final TypeName superClassName;
    private final List<TypeName> interfaces;
    private final List<TypeDefMember> members;

    public AbstractTypeDefStatement(SimpleName typeName, TypeName superClassName) {
        this(Position.noPos(), typeName, superClassName);
    }

    public AbstractTypeDefStatement(SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces) {
        this.typeName = Objects.requireNonNull(typeName);
        this.superClassName = Objects.requireNonNull(superClassName);
        this.interfaces = new ArrayList<>(interfaces);
        this.members = new ArrayList<>();
    }

    public AbstractTypeDefStatement(SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces, List<? extends TypeDefMember> members) {
        this.typeName = Objects.requireNonNull(typeName);
        this.superClassName = Objects.requireNonNull(superClassName);
        this.interfaces = new ArrayList<>(interfaces);
        this.members = new ArrayList<>(members);
    }

    public AbstractTypeDefStatement(Position position, SimpleName typeName, TypeName superClassName) {
        this(position, typeName, superClassName, new ArrayList<>(0));
    }

    public AbstractTypeDefStatement(Position position, SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces) {
        super(position);
        this.typeName = Objects.requireNonNull(typeName);
        this.superClassName = Objects.requireNonNull(superClassName);
        this.interfaces = new ArrayList<>(interfaces);
        this.members = new ArrayList<>();
    }

    public AbstractTypeDefStatement(Position position, SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces, List<? extends TypeDefMember> members) {
        super(position);
        this.typeName = Objects.requireNonNull(typeName);
        this.superClassName = Objects.requireNonNull(superClassName);
        this.interfaces = new ArrayList<>(interfaces);
        this.members = new ArrayList<>(members);
    }

    @Override
    public SimpleName getName() {
        return this.typeName;
    }

    @Override
    public TypeName getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public List<? extends TypeName> getInterfaceNames() {
        return new ArrayList<>(this.interfaces);
    }

    @Override
    public List<? extends TypeDefMember> getBody() {
        return new ArrayList<>(this.members);
    }

    public void addMember(TypeDefMember member) {
        this.members.add(Objects.requireNonNull(member));
    }
}
