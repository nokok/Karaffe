package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.names.OperatorName;

public class Div extends OperatorName {
    public Div() {
        super("/");
    }

    public Div(Position position) {
        super(position, "/");
    }

}
