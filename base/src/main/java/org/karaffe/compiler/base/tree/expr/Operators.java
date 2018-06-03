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
        case "%":
            operator.setName(Terms.varName("mod"));
            operator.setOperatorKind(OperatorKind.MOD);
            return operator;
        case "<":
            operator.setName(Terms.varName("lessThan"));
            operator.setOperatorKind(OperatorKind.LT);
            return operator;
        case ">":
            operator.setName(Terms.varName("greaterThan"));
            operator.setOperatorKind(OperatorKind.GT);
            return operator;
        case "<=":
            operator.setName(Terms.varName("lessThanEquals"));
            operator.setOperatorKind(OperatorKind.LE);
            return operator;
        case ">=":
            operator.setName(Terms.varName("greaterThanEquals"));
            operator.setOperatorKind(OperatorKind.GE);
            return operator;
        case "==":
            operator.setName(Terms.varName("kEquals"));
            operator.setOperatorKind(OperatorKind.EQEQ);
            return operator;
        case "!=":
            operator.setName(Terms.varName("notEquals"));
            operator.setOperatorKind(OperatorKind.NOTEQ);
            return operator;
        default:
            throw new IllegalArgumentException(token.getText());
        }
    }
}
