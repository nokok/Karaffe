/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTAdditionalBound;
import net.nokok.karaffe.parser.ASTAdditiveExpr;
import net.nokok.karaffe.parser.ASTAlgebraicDataCtorBinding;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeBody;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTAliasImport;
import net.nokok.karaffe.parser.ASTAmbiguousName;
import net.nokok.karaffe.parser.ASTArgument;
import net.nokok.karaffe.parser.ASTArguments;
import net.nokok.karaffe.parser.ASTAssignmentExpr;
import net.nokok.karaffe.parser.ASTAssignmentOp;
import net.nokok.karaffe.parser.ASTBinaryIntLiteral;
import net.nokok.karaffe.parser.ASTBlockStmt;
import net.nokok.karaffe.parser.ASTBlockStmts;
import net.nokok.karaffe.parser.ASTBoolAndExpr;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTBoolOrExpr;
import net.nokok.karaffe.parser.ASTCastExpr;
import net.nokok.karaffe.parser.ASTCharLiteral;
import net.nokok.karaffe.parser.ASTCheckExpr;
import net.nokok.karaffe.parser.ASTClassBody;
import net.nokok.karaffe.parser.ASTClassCtorDecl;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTClassModifiers;
import net.nokok.karaffe.parser.ASTClassOrInterfaceType;
import net.nokok.karaffe.parser.ASTClassType;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTConstantDecl;
import net.nokok.karaffe.parser.ASTConstantModifier;
import net.nokok.karaffe.parser.ASTCtorBody;
import net.nokok.karaffe.parser.ASTCtorModifier;
import net.nokok.karaffe.parser.ASTDecimalFPLiteral;
import net.nokok.karaffe.parser.ASTDecimalIntLiteral;
import net.nokok.karaffe.parser.ASTDictionaryDisplay;
import net.nokok.karaffe.parser.ASTDictionaryPair;
import net.nokok.karaffe.parser.ASTEnumDecl;
import net.nokok.karaffe.parser.ASTEnumMembers;
import net.nokok.karaffe.parser.ASTEnumModifier;
import net.nokok.karaffe.parser.ASTEnumName;
import net.nokok.karaffe.parser.ASTEqualityExpr;
import net.nokok.karaffe.parser.ASTExplicitCtorInvocation;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTExpressionName;
import net.nokok.karaffe.parser.ASTExtendsInterfaces;
import net.nokok.karaffe.parser.ASTFieldDecl;
import net.nokok.karaffe.parser.ASTFieldInitializer;
import net.nokok.karaffe.parser.ASTFieldModifier;
import net.nokok.karaffe.parser.ASTFinalModifier;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFormalParameter;
import net.nokok.karaffe.parser.ASTFormalParameters;
import net.nokok.karaffe.parser.ASTFuncAlias;
import net.nokok.karaffe.parser.ASTFuncBody;
import net.nokok.karaffe.parser.ASTFuncDecl;
import net.nokok.karaffe.parser.ASTFuncLiteralArg;
import net.nokok.karaffe.parser.ASTFuncModifier;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTFunctionName;
import net.nokok.karaffe.parser.ASTHexIntLiteral;
import net.nokok.karaffe.parser.ASTHexadecimalFPLiteral;
import net.nokok.karaffe.parser.ASTIdentifier;
import net.nokok.karaffe.parser.ASTIdentifierTypeBinding;
import net.nokok.karaffe.parser.ASTIfBody;
import net.nokok.karaffe.parser.ASTIfExpr;
import net.nokok.karaffe.parser.ASTImportAfter;
import net.nokok.karaffe.parser.ASTImportStmt;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTInterfaceBody;
import net.nokok.karaffe.parser.ASTInterfaceDecl;
import net.nokok.karaffe.parser.ASTInterfaceMember;
import net.nokok.karaffe.parser.ASTInterfaceMethodDecl;
import net.nokok.karaffe.parser.ASTInterfaceModifier;
import net.nokok.karaffe.parser.ASTInterfaceType;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTLastFormalParamter;
import net.nokok.karaffe.parser.ASTLeftHandSide;
import net.nokok.karaffe.parser.ASTListDisplay;
import net.nokok.karaffe.parser.ASTLiteral;
import net.nokok.karaffe.parser.ASTLocalValDecl;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTMethodName;
import net.nokok.karaffe.parser.ASTMultiplicativeExpr;
import net.nokok.karaffe.parser.ASTOctIntLiteral;
import net.nokok.karaffe.parser.ASTPackageDecl;
import net.nokok.karaffe.parser.ASTPostFixExpr;
import net.nokok.karaffe.parser.ASTPrimary;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTProtectedModifier;
import net.nokok.karaffe.parser.ASTPublicModifier;
import net.nokok.karaffe.parser.ASTRangeExpr;
import net.nokok.karaffe.parser.ASTRelationalExpr;
import net.nokok.karaffe.parser.ASTReturnType;
import net.nokok.karaffe.parser.ASTSimpleImport;
import net.nokok.karaffe.parser.ASTStatement;
import net.nokok.karaffe.parser.ASTStaticModifier;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTTupleDisplay;
import net.nokok.karaffe.parser.ASTTypeAliasDecl;
import net.nokok.karaffe.parser.ASTTypeAliasModifier;
import net.nokok.karaffe.parser.ASTTypeBound;
import net.nokok.karaffe.parser.ASTTypeDecl;
import net.nokok.karaffe.parser.ASTTypeName;
import net.nokok.karaffe.parser.ASTTypeParameter;
import net.nokok.karaffe.parser.ASTTypeParameters;
import net.nokok.karaffe.parser.ASTUnaryExpr;
import net.nokok.karaffe.parser.ASTUnaryExprNotPlusMinus;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.ASTVariableInitializer;
import net.nokok.karaffe.parser.ASTVariableModifier;
import net.nokok.karaffe.parser.ASTVariableType;
import net.nokok.karaffe.parser.ASTWhileLoopExpr;
import net.nokok.karaffe.parser.ParserVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousNameUtil;
import net.nokok.karaffe.parser.util.ClassModifierUtil;
import net.nokok.karaffe.parser.util.CurrentState;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BytecodeGeneratorVisitor implements ParserVisitor {

    private final CurrentState state = new CurrentState();

    public static final String CTOR_NAME = "<init>";
    public static final String CLASS_INITIALIZER = "<clinit>";

    public static final String javaLangObject = "java/lang/Object";
    public static final String arglessReturnVoid = "()V";

    private String currentPackage = ".";

    private ClassWriter createClass(int access, String className, String[] interfaces) {
        return createClass(access, className, javaLangObject, interfaces);
    }

    private ClassWriter createClass(int access, String className, String superType, String[] interfaces) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        writer.visit(Opcodes.V1_8, access, className, null, superType, interfaces);
        return writer;
    }

    @Override
    public Void visit(SimpleNode node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTAlgebraicDataTypeDecl node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTTypeParameters node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTTypeParameter node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTTypeBound node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTCompileUnit node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTArguments node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTArgument node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTVariableModifier node, Object data) throws ParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.VAR);
        return null;
    }

    @Override
    public Void visit(ASTReturnType node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTSuperType node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Void visit(ASTInterfaces node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    private void stateCheck(CurrentState.CompilerState expected) {
        if (!state.is(expected)) {
            throw new IllegalStateException();
        }
        state.setCompileState(expected);
    }

    @Override
    public Object visit(ASTPackageDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        AmbiguousNameUtil util = new AmbiguousNameUtil(node);
        currentPackage = util.getPath();
        return null;
    }

    @Override
    public Object visit(ASTImportStmt node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTSimpleImport node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAliasImport node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTImportAfter node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTTypeName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTTypeDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTClassDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        SimpleNode first = (SimpleNode) node.jjtGetChild(0);
        ASTIdentifier className;
        if (first instanceof ASTClassModifiers) {
            int modifier = (int) first.jjtAccept(this, data);
            System.out.println(modifier);
            className = (ASTIdentifier) node.jjtGetChild(1);
        } else {
            className = (ASTIdentifier) first;
        }
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Integer visit(ASTClassModifiers node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        ClassModifierUtil checker = new ClassModifierUtil(node);
        return checker.getModifier();
    }

    @Override
    public Object visit(ASTClassOrInterfaceType node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTClassType node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAdditionalBound node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceType node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTClassBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTClassCtorDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTCtorModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFormalParameters node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFormalParameter node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTLastFormalParamter node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTCtorBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTExplicitCtorInvocation node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBlockStmts node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBlockStmt node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTLocalValDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTVariableInitializer node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTVariableType node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFieldDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFieldModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFieldInitializer node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFuncDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFuncModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFuncBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAlgebraicDataTypeBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAlgebraicDataCtorBinding node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTExtendsInterfaces node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceMember node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTConstantDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTConstantModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTInterfaceMethodDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTTypeAliasDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTTypeAliasModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTEnumDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTEnumModifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTEnumName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTEnumMembers node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTExpression node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAssignmentExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAssignmentOp node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTLeftHandSide node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBoolOrExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBoolAndExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTEqualityExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTRelationalExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTRangeExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAdditiveExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMultiplicativeExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTUnaryExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTUnaryExprNotPlusMinus node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTCastExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTCheckExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTPostFixExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTPrimary node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTListDisplay node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTDictionaryDisplay node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTDictionaryPair node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTTupleDisplay node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIfExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIfBody node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTWhileLoopExpr node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIntLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTDecimalIntLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTHexIntLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTOctIntLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBinaryIntLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFloatLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTDecimalFPLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTHexadecimalFPLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTBoolLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTCharLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStringLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTUndefinedLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMethodInvocation node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTMethodName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTExpressionName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFunctionLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFuncLiteralArg node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTIdentifierTypeBinding node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAmbiguousName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStatement node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFuncAlias node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFunctionName node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTPublicModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTProtectedModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTPrivateModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTAbstractModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStaticModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTFinalModifier node, Object data) throws ParserException {
        node.childrenAccept(this, data);
        return null;
    }

}
