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
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class MethodInvocationVisitor extends KaraffeParserDefaultVisitor {

    private final Map<String, ASTExprNode> methodArgs = new HashMap<>();

    @Override
    public CtMethod visit(ASTMethodInvocation node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return null;
    }
}
