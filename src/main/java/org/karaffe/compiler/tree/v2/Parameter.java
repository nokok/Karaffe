package org.karaffe.compiler.tree.v2;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;
import org.karaffe.compiler.tree.v2.names.SimpleName;
import org.karaffe.compiler.tree.v2.names.TypeName;

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
