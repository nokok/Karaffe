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

import net.nokok.karaffe.parser.ASTKaraffeIdentifier;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class KaraffeIdentifierVisitor extends KaraffeParserDefaultVisitor {

    @Override
    public String visit(ASTKaraffeIdentifier node, Object data) throws KaraffeParserException {
        return node.jjtGetValue().toString();
    }

}
