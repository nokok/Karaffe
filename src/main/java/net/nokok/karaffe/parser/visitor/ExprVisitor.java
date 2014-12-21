/**
 *
 * Karaffe Programming Language
 *   __ _____   ___  ___   ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.visitor;

import java.util.function.Function;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class ExprVisitor extends KaraffeParserDefaultVisitor {

    @Override
    public Object visit(ASTExpression node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTExprNode node, Object data) throws KaraffeParserException {
        return node.childrenAccept(new ExprVisitor(), data);
    }

    @Override
    public Boolean visit(ASTBoolLiteral node, Object data) throws KaraffeParserException {
        return Boolean.parseBoolean(node.jjtGetValue().toString());
    }

    @Override
    public Double visit(ASTFloatLiteral node, Object data) throws KaraffeParserException {
        return Double.parseDouble(node.jjtGetValue().toString());
    }

    @Override
    public Function<?, ?> visit(ASTFunctionLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

}
