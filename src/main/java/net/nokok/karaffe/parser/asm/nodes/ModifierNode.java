/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.nodes;

import java.util.Optional;
import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTEnumDecl;
import net.nokok.karaffe.parser.ASTFinalModifier;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTInterfaceDecl;
import net.nokok.karaffe.parser.ASTOverrideModifier;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTProtectedModifier;
import net.nokok.karaffe.parser.ASTPublicModifier;
import net.nokok.karaffe.parser.ASTSealedModifier;
import net.nokok.karaffe.parser.ASTStaticModifier;
import net.nokok.karaffe.parser.ASTTypeAliasDecl;
import net.nokok.karaffe.parser.ASTVarModifier;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.Token;
import net.nokok.karaffe.parser.excptn.CompilerException;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.ErrorType;
import org.objectweb.asm.Opcodes;

public class ModifierNode {

    private final Node node;

    private int modifiers = 0;

    ModifierNode(Node node) {
        this.node = node;
    }

    public ModifierNode(ASTClassDecl node) {
        this((Node) node);
    }

    public ModifierNode(ASTAlgebraicDataTypeDecl node) {
        this((Node) node);
    }

    public ModifierNode(ASTEnumDecl node) {
        this((Node) node);
    }

    public ModifierNode(ASTInterfaceDecl node) {
        this((Node) node);
    }

    public ModifierNode(ASTTypeAliasDecl node) {
        this((Node) node);
    }

    public ModifierNode(ASTFuncDecl node) {
        this((Node) node);
    }

    public Optional<Integer> getModifier() {
        try {
            node.jjtAccept(visitor, this);
        } catch (ParserException ex) {
            return Optional.empty();
        }
        return Optional.of(modifiers);
    }

    private final ParserVisitor visitor = new ParserDefaultVisitor() {

        private boolean isOverrideVisited;

        @Override
        public void visit(ASTOverrideModifier node, Object data) throws ParserException {
            check(isOverrideVisited, node);
            throw new UnsupportedOperationException();
        }

        private boolean isVarVisited;

        @Override
        public void visit(ASTVarModifier node, Object data) throws ParserException {
            check(isVarVisited, node);
            throw new UnsupportedOperationException();
        }

        private boolean isSealedVisited;

        @Override
        public void visit(ASTSealedModifier node, Object data) throws ParserException {
            check(isSealedVisited, node);
            throw new UnsupportedOperationException();
        }

        private boolean isFinalVisited;

        @Override
        public void visit(ASTFinalModifier node, Object data) throws ParserException {
            check(isFinalVisited, node);
            modifiers += Opcodes.ACC_FINAL;
            isFinalVisited = true;
        }

        private boolean isStaticVisited;

        @Override
        public void visit(ASTStaticModifier node, Object data) throws ParserException {
            check(isStaticVisited, node);
            modifiers += Opcodes.ACC_STATIC;
            isStaticVisited = true;
        }

        private boolean isAbstractVisited;

        @Override
        public void visit(ASTAbstractModifier node, Object data) throws ParserException {
            check(isAbstractVisited, node);
            modifiers += Opcodes.ACC_ABSTRACT;
            isAbstractVisited = true;
        }

        private boolean isPrivateVisited;

        @Override
        public void visit(ASTPrivateModifier node, Object data) throws ParserException {
            check(isPrivateVisited, node);
            modifiers += Opcodes.ACC_PRIVATE;
            isPrivateVisited = true;
        }

        private boolean isProtectedVisited;

        @Override
        public void visit(ASTProtectedModifier node, Object data) throws ParserException {
            check(isProtectedVisited, node);
            modifiers += Opcodes.ACC_PROTECTED;
            isProtectedVisited = true;
        }

        private boolean isPublicVisited;

        @Override
        public void visit(ASTPublicModifier node, Object data) throws ParserException {
            check(isPublicVisited, node);
            modifiers += Opcodes.ACC_PUBLIC;
            isPublicVisited = true;
        }

        private void check(boolean cond, Node node) {
            if (cond) {
                throw new DuplicateModifierException((Token) ((SimpleNode) node).jjtGetValue());
            }
        }
    };

    public static class DuplicateModifierException extends CompilerException {

        public DuplicateModifierException(Token t) {
            super(ErrorType.DUP_MODIFIER);
        }

    }
}
