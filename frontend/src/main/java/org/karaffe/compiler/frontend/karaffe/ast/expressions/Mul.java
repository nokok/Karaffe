package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.names.OperatorName;

public class Mul extends OperatorName {
    public Mul() {
        super("*");
    }

    public Mul(Position position) {
        super(position, "*");
    }
}
