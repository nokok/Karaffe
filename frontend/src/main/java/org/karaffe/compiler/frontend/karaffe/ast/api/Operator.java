package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.antlr.v4.runtime.Token;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Div;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Minus;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Mul;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Plus;

public interface Operator {
    public static Operator of(Token terminalNode) {
        String op = terminalNode.getText();
        if ("+".equals(op)) {
            return new Plus();
        } else if ("-".equals(op)) {
            return new Minus();
        } else if ("*".equals(op)) {
            return new Mul();
        } else if ("/".equals(op)) {
            return new Div();
        } else {
            throw new IllegalStateException("invalid Operator name : " + op);
        }
    }


}
