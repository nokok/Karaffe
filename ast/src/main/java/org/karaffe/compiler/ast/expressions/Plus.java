package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.names.OperatorName;

public class Plus extends OperatorName {
    public Plus() {
        super("+");
    }

    public Plus(Position position) {
        super(position, "+");
    }

}
