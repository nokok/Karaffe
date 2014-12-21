/**
 *
 * Karaffe Programming Language
 * __ _____ ___ ___ ____________
 *   / //_/ _ | / _ \/ _ | / __/ __/ __/
 *  / , \/ __ |/ , _/ __ |/ _// _// _/
 * /_/|_/_/ |_/_/|_/_/ |_/_/ /_/ /___/
 *
 */
package net.nokok.karaffe.parser.visitor;

import java.util.HashMap;
import java.util.Map;
import javassist.CtMethod;
import net.nokok.karaffe.parser.ASTArgumentList;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTMethodOrExprName;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class MethodInvocationVisitor extends KaraffeParserDefaultVisitor {

    private final Map<String, ASTExpression> methodArgs = new HashMap<>();

    @Override
    public CtMethod visit(ASTMethodInvocation node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMethodOrExprName node, Object data) throws KaraffeParserException {
        SimpleNode n = (SimpleNode) node.jjtGetChild(0);
        String id = n.jjtGetValue().toString();
        return null;
    }

    @Override
    public Object visit(ASTArgumentList node, Object data) throws KaraffeParserException {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            node.jjtGetChild(i).jjtAccept(new ExprVisitor(), null);
        }
        return null;
    }
}
