package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;

public class This extends ExpressionName {

    public This() {
        super("this");
    }

    public This(Position position) {
        super(position, "this");
    }

}
