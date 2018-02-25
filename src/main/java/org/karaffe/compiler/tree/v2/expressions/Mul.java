package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.names.OperatorName;

public class Mul extends OperatorName {
    public Mul() {
        super("*");
    }

    public Mul(Position position) {
        super(position, "*");
    }
}
