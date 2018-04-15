package org.karaffe.compiler.frontend.karaffe.ast;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.TypeName;

public class Parameter extends AbstractTree {

    private SimpleName name;
    private TypeName type;

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

    public void setName(SimpleName name) {
        this.name = name;
    }

    public void setType(TypeName type) {
        this.type = type;
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
