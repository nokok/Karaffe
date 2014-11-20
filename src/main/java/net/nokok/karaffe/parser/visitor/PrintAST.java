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

import net.nokok.karaffe.parser.ASTAndOperator;
import net.nokok.karaffe.parser.ASTArrowOperator;
import net.nokok.karaffe.parser.ASTBangOperator;
import net.nokok.karaffe.parser.ASTColon;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTDeclaration;
import net.nokok.karaffe.parser.ASTDot;
import net.nokok.karaffe.parser.ASTEqualOperator;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTFunctionVariableBinding;
import net.nokok.karaffe.parser.ASTGreaterThanOperator;
import net.nokok.karaffe.parser.ASTHatOperator;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTInterfaceDcl;
import net.nokok.karaffe.parser.ASTLeftBrace;
import net.nokok.karaffe.parser.ASTLeftBracket;
import net.nokok.karaffe.parser.ASTLessThanOperator;
import net.nokok.karaffe.parser.ASTLiteral;
import net.nokok.karaffe.parser.ASTMinus;
import net.nokok.karaffe.parser.ASTNewLine;
import net.nokok.karaffe.parser.ASTOrOperator;
import net.nokok.karaffe.parser.ASTPercentOperator;
import net.nokok.karaffe.parser.ASTPlus;
import net.nokok.karaffe.parser.ASTQuestionOperator;
import net.nokok.karaffe.parser.ASTRightBrace;
import net.nokok.karaffe.parser.ASTRightBracket;
import net.nokok.karaffe.parser.ASTSlashOperator;
import net.nokok.karaffe.parser.ASTStarOperator;
import net.nokok.karaffe.parser.ASTStatement;
import net.nokok.karaffe.parser.ASTTildeOperator;
import net.nokok.karaffe.parser.ASTTypeDcl;
import net.nokok.karaffe.parser.ASTTypeParameter;
import net.nokok.karaffe.parser.ASTTypeParameters;
import net.nokok.karaffe.parser.ASTVariableDcl;
import net.nokok.karaffe.parser.ASTVariableTypeDcl;
import net.nokok.karaffe.parser.KaraffeParserVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;

public class PrintAST implements KaraffeParserVisitor {

    public Object defaultVisit(SimpleNode node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        node.childrenAccept(this, data);
        return data;
    }

    @Override
    public Object visit(SimpleNode node, Object data) throws KaraffeParserException {
        System.out.println("SimpleNode");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTCompileUnit node, Object data) throws KaraffeParserException {
        System.out.println("CompileUnit");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTStatement node, Object data) throws KaraffeParserException {
        System.out.println("Statement");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTDeclaration node, Object data) throws KaraffeParserException {
        System.out.println("Declaration");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTTypeDcl node, Object data) throws KaraffeParserException {
        System.out.println("TypeDcl");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTInterfaceDcl node, Object data) throws KaraffeParserException {
        System.out.println("InterfaceDcl");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTTypeParameters node, Object data) throws KaraffeParserException {
        System.out.println("TypeParameters");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTTypeParameter node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("TypeParameter");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTExpression node, Object data) throws KaraffeParserException {
        System.out.println("Expression");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTLiteral node, Object data) throws KaraffeParserException {
        System.out.println("Literal");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTFunctionLiteral node, Object data) throws KaraffeParserException {
        System.out.println("FunctionLiteral");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTFunctionVariableBinding node, Object data) throws KaraffeParserException {
        System.out.println("FunctionVariableBinding");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) throws KaraffeParserException {
        System.out.println("Identifier: " + node.jjtGetValue());
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTLeftBracket node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("[");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTRightBracket node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("]");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTLeftBrace node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("{");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTRightBrace node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("}");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTColon node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println(":");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTDot node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println(".");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTEqualOperator node, Object data) throws KaraffeParserException {
        System.out.println("EqualOperator");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTLessThanOperator node, Object data) throws KaraffeParserException {
        System.out.println("LessThanOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTGreaterThanOperator node, Object data) throws KaraffeParserException {
        System.out.println("GreaterThanOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTPlus node, Object data) throws KaraffeParserException {
        System.out.println("PlusOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTMinus node, Object data) throws KaraffeParserException {
        System.out.println("MinusOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTArrowOperator node, Object data) throws KaraffeParserException {
        System.out.println("ArrowOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTNewLine node, Object data) throws KaraffeParserException {
        System.out.println("NewLine");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTStarOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("StarOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTSlashOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("SlashOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTPercentOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("PercentOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTTildeOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("Tilde");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTBangOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("Bang");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTAndOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("And");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTOrOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("Or");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTHatOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("Hat");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTQuestionOperator node, Object data) throws net.nokok.karaffe.parser.excptn.KaraffeParserException {
        System.out.println("Question");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTVariableDcl node, Object data) throws KaraffeParserException {
        System.out.println("VariableDcl");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTVariableTypeDcl node, Object data) throws KaraffeParserException {
        System.out.println("VariableTypeDcl");
        return defaultVisit(node, data);
    }

}
