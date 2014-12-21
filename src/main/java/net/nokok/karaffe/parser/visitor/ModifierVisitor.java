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

import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTLazyModifier;
import net.nokok.karaffe.parser.ASTModifierOfFunction;
import net.nokok.karaffe.parser.ASTModifierOfOperator;
import net.nokok.karaffe.parser.ASTModifierOfType;
import net.nokok.karaffe.parser.ASTModifierOfVariable;
import net.nokok.karaffe.parser.ASTOpModifier;
import net.nokok.karaffe.parser.ASTOverrideModifier;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTSealedModifier;
import net.nokok.karaffe.parser.ASTUnaryOpModifier;
import net.nokok.karaffe.parser.ASTVariableModifier;
import net.nokok.karaffe.parser.KaraffeParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.Token;
import net.nokok.karaffe.parser.excptn.KaraffeCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.ErrorType;
import net.nokok.karaffe.parser.util.Modifiers;

public class ModifierVisitor extends KaraffeParserDefaultVisitor {

    private boolean isAbstract = false;
    private boolean isAbstractVisited = false;

    @Override
    public Object visit(ASTAbstractModifier node, Object data) throws KaraffeParserException {
        if (isAbstractVisited) {
            throwDuplicateModifierException(node);
        }
        isAbstract = true;
        isAbstractVisited = true;
        return null;
    }

    private boolean isLazy = false;
    private boolean isLazyVisited = false;

    @Override
    public Object visit(ASTLazyModifier node, Object data) throws KaraffeParserException {
        if (isLazyVisited) {
            throwDuplicateModifierException(node);
        }
        isLazy = true;
        isLazyVisited = true;
        return null;
    }

    private boolean isVar = false;
    private boolean isVarVisited = false;

    @Override
    public Object visit(ASTVariableModifier node, Object data) throws KaraffeParserException {
        if (isVarVisited) {
            throwDuplicateModifierException(node);
        }
        isVar = true;
        isVarVisited = true;
        return null;
    }

    private boolean isOp = false;
    private boolean isOpVisited = false;

    @Override
    public Object visit(ASTOpModifier node, Object data) throws KaraffeParserException {
        if (isOpVisited) {
            throwDuplicateModifierException(node);
        }
        isOp = true;
        isOpVisited = true;
        return null;
    }

    private boolean isOverride = false;
    private boolean isOverrideVisited = false;

    @Override
    public Object visit(ASTOverrideModifier node, Object data) throws KaraffeParserException {
        if (isOverrideVisited) {

        }
        isOverride = true;
        isOverrideVisited = true;
        return null;
    }

    private boolean isUnaryOp = false;
    private boolean isUnaryOpVisited = false;

    @Override
    public Object visit(ASTUnaryOpModifier node, Object data) throws KaraffeParserException {
        if (isUnaryOpVisited) {
            throwDuplicateModifierException(node);
        }
        isUnaryOp = true;
        isUnaryOpVisited = true;
        return null;
    }

    private boolean isPrivate = false;
    private boolean isPrivateVisited = false;

    @Override
    public Object visit(ASTPrivateModifier node, Object data) throws KaraffeParserException {
        if (isPrivateVisited) {
            throwDuplicateModifierException(node);
        }
        isPrivate = true;
        isPrivateVisited = true;
        return null;
    }

    private boolean isSealed = false;
    private boolean isSealedVisited = false;

    @Override
    public Object visit(ASTSealedModifier node, Object data) throws KaraffeParserException {
        if (isSealedVisited) {
            throwDuplicateModifierException(node);
        }
        isSealed = true;
        isSealedVisited = true;
        return null;
    }

    @Override
    public Modifiers visit(ASTModifierOfFunction node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return makeModifierObject(Modifiers.ModifierType.FUNCTION);
    }

    @Override
    public Modifiers visit(ASTModifierOfOperator node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return makeModifierObject(Modifiers.ModifierType.OPERATOR);
    }

    @Override
    public Modifiers visit(ASTModifierOfVariable node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return makeModifierObject(Modifiers.ModifierType.VARIABLE);
    }

    @Override
    public Modifiers visit(ASTModifierOfType node, Object data) throws KaraffeParserException {
        node.childrenAccept(this, data);
        return makeModifierObject(Modifiers.ModifierType.TYPE);
    }

    private Modifiers makeModifierObject(Modifiers.ModifierType type) {
        return new Modifiers(isAbstract, isLazy, isOp, isOverride, isPrivate, isSealed, isUnaryOp, isVar, type);
    }

    private void throwDuplicateModifierException(SimpleNode node) {
        throw new KaraffeCompilerException(ErrorType.DUP_MODIFIER, (Token) node.jjtGetValue());
    }
}
