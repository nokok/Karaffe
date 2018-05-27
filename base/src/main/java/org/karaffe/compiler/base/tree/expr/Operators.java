package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.pos.Position;

import java.util.Objects;

public interface Operators {
    static Operator byToken(Token token) {
        Objects.requireNonNull(token);
        Objects.requireNonNull(token.getText());
        Operator operator = new SimpleOperator(OperatorKind.ERROR);
        operator.setPos(Position.of(token));
        switch (token.getText()) {
        case "+":
            operator.setOperatorKind(OperatorKind.PLUS);
            return operator;
        case "-":
            operator.setOperatorKind(OperatorKind.MINUS);
            return operator;
        case "*":
            operator.setOperatorKind(OperatorKind.MUL);
            return operator;
        case "/":
            operator.setOperatorKind(OperatorKind.DIV);
            return operator;
        default:
            throw new IllegalArgumentException(token.getText());
        }
    }
}
