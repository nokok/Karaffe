/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Optional;
import net.nokok.karaffe.parser.ASTFieldDecl;
import net.nokok.karaffe.parser.ASTFieldInitializer;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTTypeName;
import net.nokok.karaffe.parser.util.AmbiguousNameUtil;
import net.nokok.karaffe.parser.util.ModifierUtil;
import net.nokok.karaffe.parser.util.NodeUtil;

public class KrfFieldNode {

    private final String owner;
    private final int modifier;
    private final String identifier;
    private final String type;
    private final ASTFieldInitializer initializer;

    public KrfFieldNode(String owner, ASTFieldDecl fieldDecl) {
        this.owner = owner;
        NodeUtil nodeUtil = new NodeUtil(fieldDecl);
        this.modifier = new ModifierUtil(fieldDecl).getModifier().orElse(0);
        this.identifier = nodeUtil.forceGetFindFirstNode(ASTIdentifier.class).jjtGetValue().toString();
        Optional<ASTTypeName> typeNode = nodeUtil.findFirstNode(ASTTypeName.class);
        if (typeNode.isPresent()) {
            this.type = new AmbiguousNameUtil(typeNode.get()).getPath();
        } else {
            this.type = "java/lang/Object";
        }
        this.initializer = nodeUtil.forceGetFindFirstNode(ASTFieldInitializer.class);
    }

    public int getModifier() {
        return modifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getType() {
        return type;
    }

    public ASTFieldInitializer getInitializer() {
        return initializer;
    }

}
