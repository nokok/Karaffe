package org.karaffe.compiler.tree.v2.expressions;

import org.karaffe.compiler.pos.Position;

public class This extends ExpressionName {

    public This() {
        super("this");
    }

    public This(Position position) {
        super(position, "this");
    }

}
