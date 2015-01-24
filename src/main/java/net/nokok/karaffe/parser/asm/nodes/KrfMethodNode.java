/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.util.ModifierUtil;
import net.nokok.karaffe.parser.util.NodeUtil;
import org.objectweb.asm.tree.MethodNode;

public class KrfMethodNode {

    private final MethodNode methodNode = new MethodNode();

    public KrfMethodNode(ASTFuncDecl node) {
        NodeUtil nodeUtil = new NodeUtil(node);
        methodNode.access = new ModifierUtil(node).getModifier().orElse(0);
        methodNode.name = nodeUtil.forceGetFindFirstNode(ASTIdentifier.class).jjtGetValue().toString();
    }
}
