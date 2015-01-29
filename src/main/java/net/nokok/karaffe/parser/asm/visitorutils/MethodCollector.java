/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.visitorutils;

import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.asm.nodes.ModifierNode;
import net.nokok.karaffe.parser.asm.nodes.NodeUtil;
import net.nokok.karaffe.parser.excptn.ParserException;

public class MethodCollector {

    private final Node node;

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        @Override
        public Object visit(ASTFuncDecl node, Object data) throws ParserException {
            ModifierNode modifierNode = new ModifierNode(node);
            int modifier = modifierNode.getModifier().orElse(0);
            NodeUtil nodeUtil = new NodeUtil(node);
            String name = nodeUtil.forceGetFindFirstNode(ASTIdentifier.class).jjtGetValue().toString();
            return null;
        }

    };

    public MethodCollector(ASTClassDecl clazz) {
        this.node = clazz;
    }
}
