package org.karaffe.compiler.ast.statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.Parameter;
import org.karaffe.compiler.ast.api.AbstractTree;
import org.karaffe.compiler.ast.api.Modifier;
import org.karaffe.compiler.ast.api.Statement;
import org.karaffe.compiler.ast.api.StatementType;
import org.karaffe.compiler.ast.api.TypeDefMember;
import org.karaffe.compiler.ast.modifiers.Public;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

public class MethodDef extends AbstractTree implements TypeDefMember {

    private final List<Modifier> modifiers;
    private final TypeName returnType;
    private final SimpleName methodName;
    private final List<Parameter> parameters;
    private final List<Statement> methodBody;

    public MethodDef(TypeName returnType, SimpleName methodName, List<? extends Statement> body) {
        this(new ArrayList<>(Arrays.asList(new Public())), returnType, methodName, new ArrayList<>(0), body);
    }

    public MethodDef(List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName) {
        this(modifiers, returnType, methodName, new ArrayList<>(0));
    }

    public MethodDef(List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName, List<? extends Parameter> parameters) {
        this(Position.noPos(), modifiers, returnType, methodName, parameters);
    }

    public MethodDef(List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName, List<? extends Parameter> parameters, List<? extends Statement> body) {
        this(Position.noPos(), modifiers, returnType, methodName, parameters, body);
    }

    public MethodDef(Position position, List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName) {
        this(position, modifiers, returnType, methodName, new ArrayList<>(0));
    }

    public MethodDef(Position position, List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName, List<? extends Parameter> parameters) {
        this(position, modifiers, returnType, methodName, parameters, new ArrayList<>());
    }

    public MethodDef(Position position, List<? extends Modifier> modifiers, TypeName returnType, SimpleName methodName, List<? extends Parameter> parameters, List<? extends Statement> body) {
        super(position);
        this.modifiers = new ArrayList<>(modifiers);
        this.returnType = Objects.requireNonNull(returnType);
        this.methodName = Objects.requireNonNull(methodName);
        this.parameters = new ArrayList<>(parameters);
        this.methodBody = new ArrayList<>(body);
    }

    public MethodDef(MethodDef other) {
        this(other.getPosition(), other.getModifiers(), other.getReturnTypeName(), other.getName(), other.getParameters(), other.getBody());
    }

    public void addMethodBody(Statement statement) {
        this.methodBody.add(Objects.requireNonNull(statement));
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.METHOD_DEF;
    }

    public List<? extends Modifier> getModifiers() {
        return new ArrayList<>(this.modifiers);
    }

    public TypeName getReturnTypeName() {
        return this.returnType;
    }

    @Override
    public SimpleName getName() {
        return this.methodName;
    }

    public List<? extends Parameter> getParameters() {
        return new ArrayList<>(this.parameters);
    }

    public List<? extends Statement> getBody() {
        return new ArrayList<>(this.methodBody);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s(%s) {\n%s\n}",
                String.join(" ", this.modifiers.stream().map(Modifier::toString).collect(Collectors.toList())),
                this.returnType,
                this.methodName,
                String.join(",", this.parameters.stream().map(Parameter::toString).collect(Collectors.toList())),
                String.join("\n", this.methodBody.stream().map(Statement::toString).map(s -> s + ";").collect(Collectors.toList())));
    }

}
