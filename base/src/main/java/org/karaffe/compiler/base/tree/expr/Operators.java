package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.pos.Position;

import java.util.Objects;
import java.util.stream.Stream;

public interface Operators {
    static Operator byToken(Token token) {
        Objects.requireNonNull(token);
        Objects.requireNonNull(token.getText());
        Operator operator = new SimpleOperator(OperatorKind.ERROR);
        operator.setPos(Position.of(token));
        OperatorKind operatorKind = Stream
                .of(OperatorKind.values())
                .filter(n -> n.getSimpleName().equals(token.getText()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(token.getText()));
        operator.setOperatorKind(operatorKind);
        return operator;
    }
}
