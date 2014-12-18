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

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import net.nokok.karaffe.parser.ASTAA;
import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTAnd;
import net.nokok.karaffe.parser.ASTAnnotation;
import net.nokok.karaffe.parser.ASTArgumentList;
import net.nokok.karaffe.parser.ASTArguments;
import net.nokok.karaffe.parser.ASTAssign;
import net.nokok.karaffe.parser.ASTAssignment;
import net.nokok.karaffe.parser.ASTAssignmentExpression;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTClosedRange;
import net.nokok.karaffe.parser.ASTComparable;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTDA;
import net.nokok.karaffe.parser.ASTElementAccess;
import net.nokok.karaffe.parser.ASTEndOfModule;
import net.nokok.karaffe.parser.ASTEnumDeclaration;
import net.nokok.karaffe.parser.ASTEnumElements;
import net.nokok.karaffe.parser.ASTEqualTo;
import net.nokok.karaffe.parser.ASTExplicitModuleElementAccess;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTExpressionName;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTGreaterThan;
import net.nokok.karaffe.parser.ASTGreaterThanEqualTo;
import net.nokok.karaffe.parser.ASTHalfOpenRange;
import net.nokok.karaffe.parser.ASTHat;
import net.nokok.karaffe.parser.ASTImportStatement;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTInterfaceDeclaration;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTJavaFQCN;
import net.nokok.karaffe.parser.ASTKaraffeIdentifier;
import net.nokok.karaffe.parser.ASTLazyModifier;
import net.nokok.karaffe.parser.ASTLeftHandSide;
import net.nokok.karaffe.parser.ASTLessThan;
import net.nokok.karaffe.parser.ASTLessThanEqualTo;
import net.nokok.karaffe.parser.ASTMA;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTMethodName;
import net.nokok.karaffe.parser.ASTMinus;
import net.nokok.karaffe.parser.ASTModifierOfFunction;
import net.nokok.karaffe.parser.ASTModifierOfOperator;
import net.nokok.karaffe.parser.ASTModifierOfType;
import net.nokok.karaffe.parser.ASTModifierOfVariable;
import net.nokok.karaffe.parser.ASTModuleDeclarationStatement;
import net.nokok.karaffe.parser.ASTNewLineToken;
import net.nokok.karaffe.parser.ASTNonComparable;
import net.nokok.karaffe.parser.ASTNotEqualTo;
import net.nokok.karaffe.parser.ASTOpModifier;
import net.nokok.karaffe.parser.ASTOr;
import net.nokok.karaffe.parser.ASTOverrideModifier;
import net.nokok.karaffe.parser.ASTPercent;
import net.nokok.karaffe.parser.ASTPlus;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTRA;
import net.nokok.karaffe.parser.ASTSA;
import net.nokok.karaffe.parser.ASTSafeDiv;
import net.nokok.karaffe.parser.ASTSafeRem;
import net.nokok.karaffe.parser.ASTSealedModifier;
import net.nokok.karaffe.parser.ASTSingleArrow;
import net.nokok.karaffe.parser.ASTSlash;
import net.nokok.karaffe.parser.ASTStar;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTTypeAliasDeclaration;
import net.nokok.karaffe.parser.ASTTypeBound;
import net.nokok.karaffe.parser.ASTTypeDeclaration;
import net.nokok.karaffe.parser.ASTTypeParameter;
import net.nokok.karaffe.parser.ASTTypeParameters;
import net.nokok.karaffe.parser.ASTUnaryBang;
import net.nokok.karaffe.parser.ASTUnaryMinus;
import net.nokok.karaffe.parser.ASTUnaryOpModifier;
import net.nokok.karaffe.parser.ASTUnaryPlus;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.ASTVariableModifier;
import net.nokok.karaffe.parser.ASTVariableOrFunctionDeclaration;
import net.nokok.karaffe.parser.KaraffeParserVisitor;
import net.nokok.karaffe.parser.Node;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.InternalCompilerException;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class BytecodeGenerator implements KaraffeParserVisitor {

    private final ClassPool CLASS_POOL = ClassPool.getDefault();

    private CtClass currentClass;

    private final Map<String, byte[]> classes = new HashMap<>();

    @Override
    public Object visit(ASTCompileUnit node, Object data) throws KaraffeParserException {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            Node n = node.jjtGetChild(i);
            if (n instanceof ASTNewLineToken
                    && (i + 1 < node.jjtGetNumChildren())
                    && node.jjtGetChild(i + 1) instanceof ASTNewLineToken) {
                encountedBlankLine();
            } else {
                n.jjtAccept(this, data);
            }
        }
        return null;
    }

    private void encountedBlankLine() {
        try {
            classes.put(currentClass.getName(), currentClass.toBytecode());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (CannotCompileException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String visit(ASTStringLiteral node, Object data) throws KaraffeParserException {
        return (String) node.jjtGetValue();
    }

    public Map<String, byte[]> toByteArrays() {
        return Collections.unmodifiableMap(classes);
    }

    @Override
    public Object visit(SimpleNode node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTNewLineToken node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTEnumElements node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    private void printNode(SimpleNode node) throws KaraffeParserException {
        System.out.println(node.jjtGetNumChildren());
    }

    @Override
    public Object visit(ASTExplicitModuleElementAccess node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTBoolLiteral node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTIntLiteral node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTFloatLiteral node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUndefinedLiteral node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTEndOfModule node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTKaraffeIdentifier node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTJavaFQCN node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTExpression node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAssign node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTOr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAnd node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTEqualTo node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTNotEqualTo node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTLessThan node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTGreaterThanEqualTo node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTLessThanEqualTo node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTComparable node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTNonComparable node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTPlus node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTMinus node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTStar node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSlash node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTPercent node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSafeDiv node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSafeRem node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUnaryBang node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUnaryMinus node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModuleDeclarationStatement node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTTypeDeclaration node, Object data) throws KaraffeParserException {
        int childrenSize = node.jjtGetNumChildren();
        if (childrenSize == 0) {
            throw new InternalCompilerException(node, "子ノードの数が不正です");
        }
        currentClass = CLASS_POOL.makeClass("");
        return null;
    }

    @Override
    public Object visit(ASTInterfaceDeclaration node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTTypeAliasDeclaration node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAbstractModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTOverrideModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTPrivateModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSealedModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTOpModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUnaryOpModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTVariableModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTVariableOrFunctionDeclaration node, Object data) throws KaraffeParserException {
        //全部Fieldで宣言すると楽かも？
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTFunctionLiteral node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTTypeParameters node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTTypeParameter node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTTypeBound node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSingleArrow node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAssignmentExpression node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAssignment node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTLeftHandSide node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTElementAccess node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTExprNode node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUnaryPlus node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTHat node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTClosedRange node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTHalfOpenRange node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAA node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSA node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTMA node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTDA node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTRA node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTImportStatement node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAnnotation node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTLazyModifier node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTEnumDeclaration node, Object data) throws KaraffeParserException {

        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTSuperType node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModifierOfType node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModifierOfVariable node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModifierOfFunction node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModifierOfOperator node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTInterfaces node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTMethodInvocation node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTMethodName node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTExpressionName node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTArguments node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTArgumentList node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

}
