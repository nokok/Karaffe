package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.names.OperatorName;

public class Div extends OperatorName {
    public Div() {
        super("/");
    }

    public Div(Position position) {
        super(position, "/");
    }

}
