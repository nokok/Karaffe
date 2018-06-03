package org.karaffe.compiler.base.tree.expr;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.term.Terms;

import java.util.Objects;

public interface Operators {
    static Operator byToken(Token token) {
        Objects.requireNonNull(token);
        Objects.requireNonNull(token.getText());
        Operator operator = new SimpleOperator(OperatorKind.ERROR);
        operator.setPos(Position.of(token));
        switch (token.getText()) {
        case "+":
            operator.setName(Terms.varName("add"));
            operator.setOperatorKind(OperatorKind.PLUS);
            return operator;
        case "-":
            operator.setName(Terms.varName("sub"));
            operator.setOperatorKind(OperatorKind.MINUS);
            return operator;
        case "*":
            operator.setName(Terms.varName("mul"));
            operator.setOperatorKind(OperatorKind.MUL);
            return operator;
        case "/":
            operator.setName(Terms.varName("div"));
            operator.setOperatorKind(OperatorKind.DIV);
            return operator;
        default:
            throw new IllegalArgumentException(token.getText());
        }
    }
}
