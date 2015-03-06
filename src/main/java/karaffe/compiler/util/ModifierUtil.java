/**
 * Karaffe Programming Language
 */
package karaffe.compiler.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import karaffe.compiler.ParserException;
import karaffe.compiler.phase.parser.ASTAbstractModifier;
import karaffe.compiler.phase.parser.ASTFinalModifier;
import karaffe.compiler.phase.parser.ASTPrivateModifier;
import karaffe.compiler.phase.parser.ASTProtectedModifier;
import karaffe.compiler.phase.parser.ASTPublicModifier;
import karaffe.compiler.phase.parser.ASTStaticModifier;
import karaffe.compiler.phase.parser.ASTVariableModifier;
import karaffe.compiler.phase.parser.Node;
import karaffe.compiler.phase.parser.ParserDefaultVisitor;
import org.objectweb.asm.Opcodes;

public class ModifierUtil extends ParserDefaultVisitor {

    private final List<String> nodes = new ArrayList<>();

    private final Node node;

    private int modifier = 0;

    public ModifierUtil(Node node) {
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

    public Optional<Integer> getModifier() {
        modifier = 0;
        try {
            node.jjtAccept(this, this);
            if (hasDuplicatingModifier()) {
                return Optional.empty();
            } else {
                return Optional.of(modifier);
            }
        } catch (ParserException e) {
            return Optional.empty();
        }
    }

    private boolean isAbstractVisited;

    @Override
    public void visit(ASTAbstractModifier node, Object data) throws ParserException {
        if (isAbstractVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_ABSTRACT;
        isAbstractVisited = true;
    }

    private boolean isProtectedVisited;

    @Override
    public void visit(ASTProtectedModifier node, Object data) throws ParserException {
        if (isProtectedVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_PROTECTED;
        isProtectedVisited = true;
    }

    private boolean isPrivateVisited;

    @Override
    public void visit(ASTPrivateModifier node, Object data) throws ParserException {
        if (isPrivateVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_PRIVATE;
        isPrivateVisited = true;
    }

    private boolean isPublicVisited;

    @Override
    public void visit(ASTPublicModifier node, Object data) throws ParserException {
        if (isPublicVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_PUBLIC;
        isPublicVisited = true;
    }

    private boolean isStaticVisited;

    @Override
    public void visit(ASTStaticModifier node, Object data) throws ParserException {
        if (isStaticVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_STATIC;
        isStaticVisited = true;
    }

    private boolean isFinalVisited;

    @Override
    public void visit(ASTFinalModifier node, Object data) throws ParserException {
        if (isFinalVisited) {
            throw new RuntimeException();
        }
        modifier += Opcodes.ACC_FINAL;
        isFinalVisited = true;
    }

    @Override
    public void visit(ASTVariableModifier node, Object data) throws ParserException {
    }

}
