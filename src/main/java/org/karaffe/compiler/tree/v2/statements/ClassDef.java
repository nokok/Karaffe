package org.karaffe.compiler.tree.v2.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.api.ClassMember;
import org.karaffe.compiler.tree.v2.api.Statement;
import org.karaffe.compiler.tree.v2.api.StatementType;
import org.karaffe.compiler.tree.v2.api.TypeDefStatement;
import org.karaffe.compiler.tree.v2.names.SimpleName;

public class ClassDef extends AbstractTree implements TypeDefStatement {

    private final SimpleName className;
    private final SimpleName superClassName;
    private final List<SimpleName> interfaces = new ArrayList<>(0);
    private final List<ClassMember> body = new ArrayList<>();

    public ClassDef(SimpleName className, SimpleName superClassName) {
        this.className = className;
        this.superClassName = superClassName;
    }

    public ClassDef(Position position, SimpleName className, SimpleName superClassName) {
        super(position);
        this.className = className;
        this.superClassName = superClassName;
    }

    public void addBody(ClassMember statement) {
        this.body.add(Objects.requireNonNull(statement));
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.CLASS_DEF;
    }

    @Override
    public boolean isInterfaceDecl() {
        return false;
    }

    @Override
    public boolean isClassDecl() {
        return true;
    }

    @Override
    public SimpleName getName() {
        return this.className;
    }

    @Override
    public SimpleName getSuperClass() {
        return this.superClassName;
    }

    @Override
    public List<? extends SimpleName> getInterfaces() {
        return new ArrayList<>(this.interfaces);
    }

    @Override
    public List<? extends Statement> getBody() {
        return new ArrayList<>(this.body);
    }

    @Override
    public String toString() {
        return String.format("class %s extends %s%s {\n"
                + "%s\n"
                + "}",
                this.className,
                this.superClassName,
                this.interfaces.isEmpty() ? "" : "implements " + String.join(", ", this.interfaces),
                String.join(";\n", this.body.stream().map(ClassMember::toString).collect(Collectors.toList())));
    }

}
