package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.names.OperatorName;

public class Plus extends OperatorName {
    public Plus() {
        super("+");
    }

    public Plus(Position position) {
        super(position, "+");
    }

}
