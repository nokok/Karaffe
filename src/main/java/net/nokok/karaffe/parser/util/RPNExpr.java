/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.SimpleNode;

public class RPNExpr {

    private final List<SimpleNode> output = new ArrayList<>();

    private final Stack<SimpleNode> stack = new Stack<>();

    public RPNExpr(ASTExpression nodes) {
        for (int i = 0; i < nodes.jjtGetNumChildren(); i++) {
            SimpleNode child = (SimpleNode) nodes.jjtGetChild(i);
            if (isLiteral(child)) {
                output.add(child);
            } else if (child instanceof ASTExprNode) {
                stack.push(child);
            }
        }
    }

    private boolean isLiteral(SimpleNode node) {
        return node instanceof ASTBoolLiteral
                || node instanceof ASTFloatLiteral
                || node instanceof ASTFunctionLiteral
                || node instanceof ASTIntLiteral
                || node instanceof ASTStringLiteral
                || node instanceof ASTUndefinedLiteral;
    }

    public List<SimpleNode> getNodes() {
        return output;
    }
}
