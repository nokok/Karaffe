package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.names.OperatorName;

public class Mul extends OperatorName {
    public Mul() {
        super("*");
    }

    public Mul(Position position) {
        super(position, "*");
    }
}
