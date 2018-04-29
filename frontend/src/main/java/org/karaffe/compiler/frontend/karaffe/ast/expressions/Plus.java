package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.names.OperatorName;

public class Plus extends OperatorName {
    public Plus() {
        super("+");
    }

    public Plus(Position position) {
        super(position, "+");
    }

}
