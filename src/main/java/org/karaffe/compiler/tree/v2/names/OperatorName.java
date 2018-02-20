package org.karaffe.compiler.tree.v2.names;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.Operator;

public abstract class OperatorName extends SimpleName implements Operator {

    public OperatorName(Position position, SimpleName other) {
        super(position, other);
    }

    public OperatorName(Position position, String name) {
        super(position, name);
    }

    public OperatorName(SimpleName other) {
        super(other);
    }

    public OperatorName(String name) {
        super(name);
    }

}
