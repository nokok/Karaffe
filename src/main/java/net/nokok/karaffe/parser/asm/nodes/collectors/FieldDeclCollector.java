/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes.collectors;

import net.nokok.karaffe.parser.ASTFieldDecl;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;

public class FieldDeclCollector {

    private final ParserVisitor fieldVisitor = new ParserDefaultVisitor() {

        @Override
        public void visit(ASTFieldDecl node, Object data) throws ParserException {

        }

    };

}
