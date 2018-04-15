package org.karaffe.compiler.frontend.karaffe.ast.expressions;

import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.names.OperatorName;

public class Minus extends OperatorName {
    public Minus() {
        super("-");
    }

    public Minus(Position position) {
        super(position, "-");
    }
}
