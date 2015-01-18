/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTFinalModifier;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTProtectedModifier;
import net.nokok.karaffe.parser.ASTPublicModifier;
import net.nokok.karaffe.parser.ASTStaticModifier;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.excptn.ParserException;
import org.objectweb.asm.Opcodes;

public class ClassModifierUtil extends ParserDefaultVisitor {

    private final List<String> nodes = new ArrayList<>();

    private final Node node;

    private int modifier = 0;

    public ClassModifierUtil(Node node) {
        this.node = Objects.requireNonNull(node);
    }

    public boolean hasDuplicatingModifier() {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            String className = node.jjtGetChild(i).getClass().getName();
            if (!nodes.contains(className)) {
                nodes.add(className);
            } else {
                return true;
            }
        }
        return false;
    }

    public int getModifier() throws ParserException {
        modifier = 0;
        node.jjtAccept(this, this);
        return modifier;
    }

    @Override
    public Object visit(ASTAbstractModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_ABSTRACT;
        return null;
    }

    @Override
    public Object visit(ASTProtectedModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_PROTECTED;
        return null;
    }

    @Override
    public Object visit(ASTPrivateModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_PRIVATE;
        return null;
    }

    @Override
    public Object visit(ASTPublicModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_PUBLIC;
        return null;
    }

    @Override
    public Object visit(ASTStaticModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_STATIC;
        return null;
    }

    @Override
    public Object visit(ASTFinalModifier node, Object data) throws ParserException {
        modifier += Opcodes.ACC_FINAL;
        return null;
    }

}
