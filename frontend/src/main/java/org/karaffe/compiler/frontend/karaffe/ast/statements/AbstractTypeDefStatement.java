package org.karaffe.compiler.frontend.karaffe.ast.statements;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.api.Statement;
import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTypeDefStatement extends AbstractTree implements TypeDefStatement {
    private SimpleName typeName;
    private TypeName superClassName;
    private List<TypeName> interfaces = new ArrayList<>();
    private List<Statement> members = new ArrayList<>();

    public AbstractTypeDefStatement() {

    }

    public AbstractTypeDefStatement(SimpleName typeName, TypeName superClassName) {
        this(Position.noPos(), typeName, superClassName);
    }

    public AbstractTypeDefStatement(SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces, List<? extends Statement> members) {
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

    public AbstractTypeDefStatement(Position position, SimpleName typeName, TypeName superClassName, List<? extends TypeName> interfaces, List<? extends Statement> members) {
        super(position);
        this.typeName = Objects.requireNonNull(typeName);
        this.superClassName = Objects.requireNonNull(superClassName);
        this.interfaces = new ArrayList<>(interfaces);
        this.members = new ArrayList<>(members);
    }

    @Override
    public void addInterface(TypeName impl) {
        this.interfaces.add(impl);
    }

    @Override
    public void replaceInterfaces(List<TypeName> interfaces) {
        this.interfaces = new ArrayList<>(interfaces);
    }

    @Override
    public void replaceBody(List<Statement> members) {
        this.members.clear();
        for (Statement member : members) {
            if (member != null) {
                this.members.add(member);
            }
        }
    }

    @Override
    public SimpleName getName() {
        return this.typeName;
    }

    @Override
    public void setName(SimpleName className) {
        this.typeName = className;
    }

    @Override
    public TypeName getSuperClassName() {
        return this.superClassName;
    }

    @Override
    public void setSuperClassName(TypeName superClassName) {
        this.superClassName = superClassName;
    }

    @Override
    public List<? extends TypeName> getInterfaceNames() {
        return new ArrayList<>(this.interfaces);
    }

    @Override
    public List<? extends Statement> getBody() {
        return new ArrayList<>(this.members);
    }

    public void addMember(Statement member) {
        this.members.add(Objects.requireNonNull(member));
    }
}
