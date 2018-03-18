package org.karaffe.compiler.ast;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.api.AbstractTree;
import org.karaffe.compiler.ast.names.SimpleName;
import org.karaffe.compiler.ast.names.TypeName;

public class Parameter extends AbstractTree {

    private final SimpleName name;
    private final TypeName type;

    public Parameter(SimpleName name, TypeName type) {
        this(Position.noPos(), name, type);
    }

    public Parameter(Position position, SimpleName name, TypeName type) {
        super(position);
        this.name = name;
        this.type = type;
    }

    public Parameter(Parameter parameter) {
        this(parameter.getPosition(), parameter.getName(), parameter.getType());
    }

    public SimpleName getName() {
        return this.name;
    }

    public TypeName getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.type);
    }

}
