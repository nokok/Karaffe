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

import net.nokok.karaffe.parser.ASTAdditiveOpExpr;
import net.nokok.karaffe.parser.ASTAndOpExpr;
import net.nokok.karaffe.parser.ASTAssignExpr;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTComparisonOpExpr;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTEndOfModule;
import net.nokok.karaffe.parser.ASTExplicitModuleElementAccess;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTModifier;
import net.nokok.karaffe.parser.ASTModuleStatement;
import net.nokok.karaffe.parser.ASTMultiplyOpExpr;
import net.nokok.karaffe.parser.ASTNewLineToken;
import net.nokok.karaffe.parser.ASTOrOpExpr;
import net.nokok.karaffe.parser.ASTPrimaryExpr;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTUnaryOpExpr;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.ASTVariableDeclaration;
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
    public Object visit(ASTNewLineToken node, Object data) throws KaraffeParserException {
        System.out.println("[nl]");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTModuleStatement node, Object data) throws KaraffeParserException {
        System.out.println("ModuleStatement");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTVariableDeclaration node, Object data) throws KaraffeParserException {
        System.out.println("VariableDeclaration");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTStringLiteral node, Object data) throws KaraffeParserException {
        System.out.println("StringLiteral");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTModifier node, Object data) throws KaraffeParserException {
        System.out.println("Modifier");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTOrOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("Or");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTAndOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("And");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTComparisonOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("Comparison");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTAdditiveOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("Additive");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTMultiplyOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("Multiply");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTUnaryOpExpr node, Object data) throws KaraffeParserException {
        System.out.println("UnaryOp");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTPrimaryExpr node, Object data) throws KaraffeParserException {
        System.out.println("PrimaryExpr");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTAssignExpr node, Object data) throws KaraffeParserException {
        System.out.println("AssignExpr");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTExplicitModuleElementAccess node, Object data) throws KaraffeParserException {
        System.out.println("ExplicitModuleElementAccess");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTBoolLiteral node, Object data) throws KaraffeParserException {
        System.out.println("Bool");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTIntLiteral node, Object data) throws KaraffeParserException {
        System.out.println("Int");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTFloatLiteral node, Object data) throws KaraffeParserException {
        System.out.println("FloatOrDouble");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTUndefinedLiteral node, Object data) throws KaraffeParserException {
        System.out.println("Undefined");
        return defaultVisit(node, data);
    }

    @Override
    public Object visit(ASTEndOfModule node, Object data) throws KaraffeParserException {
        System.out.println("EndOfModule");
        return node.childrenAccept(this, data);
    }
}
