package org.karaffe.compiler.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.ast.names.OperatorName;

public class Minus extends OperatorName {
    public Minus() {
        super("-");
    }

    public Minus(Position position) {
        super(position, "-");
    }
}
