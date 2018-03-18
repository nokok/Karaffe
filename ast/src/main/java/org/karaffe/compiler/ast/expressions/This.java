package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;

public class This extends ExpressionName {

    public This() {
        super("this");
    }

    public This(Position position) {
        super(position, "this");
    }

}
