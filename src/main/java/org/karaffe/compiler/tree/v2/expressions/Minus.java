package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.names.OperatorName;

public class Minus extends OperatorName {
    public Minus() {
        super("-");
    }

    public Minus(Position position) {
        super(position, "-");
    }
}
