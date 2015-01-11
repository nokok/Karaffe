/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import net.nokok.karaffe.parser.ASTAA;
import net.nokok.karaffe.parser.ASTAbstractModifier;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDeclBody;
import net.nokok.karaffe.parser.ASTAlias;
import net.nokok.karaffe.parser.ASTAnd;
import net.nokok.karaffe.parser.ASTAnnotation;
import net.nokok.karaffe.parser.ASTArgVariable;
import net.nokok.karaffe.parser.ASTArgVariables;
import net.nokok.karaffe.parser.ASTArgument;
import net.nokok.karaffe.parser.ASTArguments;
import net.nokok.karaffe.parser.ASTAssign;
import net.nokok.karaffe.parser.ASTAssignment;
import net.nokok.karaffe.parser.ASTBody;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTCastExpression;
import net.nokok.karaffe.parser.ASTClosedRange;
import net.nokok.karaffe.parser.ASTComparable;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTConstructor;
import net.nokok.karaffe.parser.ASTDA;
import net.nokok.karaffe.parser.ASTDocumentation;
import net.nokok.karaffe.parser.ASTElementAccess;
import net.nokok.karaffe.parser.ASTEnumDeclaration;
import net.nokok.karaffe.parser.ASTEnumElements;
import net.nokok.karaffe.parser.ASTEqualTo;
import net.nokok.karaffe.parser.ASTExistingTypeOrTParameter;
import net.nokok.karaffe.parser.ASTExprName;
import net.nokok.karaffe.parser.ASTExprNode;
import net.nokok.karaffe.parser.ASTExpressionBinding;
import net.nokok.karaffe.parser.ASTExpressionName;
import net.nokok.karaffe.parser.ASTExtendsType;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFuncAliasAfter;
import net.nokok.karaffe.parser.ASTFuncAliasBefore;
import net.nokok.karaffe.parser.ASTFuncAliasDeclaration;
import net.nokok.karaffe.parser.ASTFuncPattern;
import net.nokok.karaffe.parser.ASTFunctionDeclBody;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTFunctionLiteralParameters;
import net.nokok.karaffe.parser.ASTFunctionType;
import net.nokok.karaffe.parser.ASTGreaterThan;
import net.nokok.karaffe.parser.ASTGreaterThanEqualTo;
import net.nokok.karaffe.parser.ASTGuard;
import net.nokok.karaffe.parser.ASTHalfOpenRange;
import net.nokok.karaffe.parser.ASTHat;
import net.nokok.karaffe.parser.ASTImportStatement;
import net.nokok.karaffe.parser.ASTIntLiteral;
import net.nokok.karaffe.parser.ASTInterfaceDeclaration;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTJavaFQCN;
import net.nokok.karaffe.parser.ASTJavaType;
import net.nokok.karaffe.parser.ASTLazyModifier;
import net.nokok.karaffe.parser.ASTLeftHandSide;
import net.nokok.karaffe.parser.ASTLessThan;
import net.nokok.karaffe.parser.ASTLessThanEqualTo;
import net.nokok.karaffe.parser.ASTListLiteral;
import net.nokok.karaffe.parser.ASTMA;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTMethodName;
import net.nokok.karaffe.parser.ASTMinus;
import net.nokok.karaffe.parser.ASTModifierStatement;
import net.nokok.karaffe.parser.ASTModuleBody;
import net.nokok.karaffe.parser.ASTModuleDeclaration;
import net.nokok.karaffe.parser.ASTModuleName;
import net.nokok.karaffe.parser.ASTNewInstance;
import net.nokok.karaffe.parser.ASTNonComparable;
import net.nokok.karaffe.parser.ASTNotEqualTo;
import net.nokok.karaffe.parser.ASTOr;
import net.nokok.karaffe.parser.ASTOverrideModifier;
import net.nokok.karaffe.parser.ASTPatternBody;
import net.nokok.karaffe.parser.ASTPercent;
import net.nokok.karaffe.parser.ASTPlus;
import net.nokok.karaffe.parser.ASTPrivateModifier;
import net.nokok.karaffe.parser.ASTPublicModifier;
import net.nokok.karaffe.parser.ASTRA;
import net.nokok.karaffe.parser.ASTReturnType;
import net.nokok.karaffe.parser.ASTSA;
import net.nokok.karaffe.parser.ASTSP;
import net.nokok.karaffe.parser.ASTSafeDiv;
import net.nokok.karaffe.parser.ASTSafeRem;
import net.nokok.karaffe.parser.ASTSealedModifier;
import net.nokok.karaffe.parser.ASTSingleArrow;
import net.nokok.karaffe.parser.ASTSlash;
import net.nokok.karaffe.parser.ASTStar;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTSwitchCase;
import net.nokok.karaffe.parser.ASTSwitchExpr;
import net.nokok.karaffe.parser.ASTTupleLiteral;
import net.nokok.karaffe.parser.ASTTypeAliasDeclaration;
import net.nokok.karaffe.parser.ASTTypeBound;
import net.nokok.karaffe.parser.ASTTypeDeclBody;
import net.nokok.karaffe.parser.ASTTypeDeclaration;
import net.nokok.karaffe.parser.ASTTypeElement;
import net.nokok.karaffe.parser.ASTTypeElementBinding;
import net.nokok.karaffe.parser.ASTTypeIdentifier;
import net.nokok.karaffe.parser.ASTTypeParameter;
import net.nokok.karaffe.parser.ASTTypeParameters;
import net.nokok.karaffe.parser.ASTUnaryBang;
import net.nokok.karaffe.parser.ASTUnaryMinus;
import net.nokok.karaffe.parser.ASTUnaryPlus;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.ASTUnitType;
import net.nokok.karaffe.parser.ASTValueType;
import net.nokok.karaffe.parser.ASTVarArgs;
import net.nokok.karaffe.parser.ASTVarIdentifier;
import net.nokok.karaffe.parser.ASTVariableModifier;
import net.nokok.karaffe.parser.ASTVariableOrFunctionDeclaration;
import net.nokok.karaffe.parser.ASTWildCard;
import net.nokok.karaffe.parser.KaraffeParserVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.excptn.KaraffeParserException;
import net.nokok.karaffe.parser.util.CurrentState;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BytecodeGeneratorVisitor implements KaraffeParserVisitor {

    private final CurrentState state = new CurrentState();

    public static final String CTOR_NAME = "<init>";
    public static final String CLASS_INITIALIZER = "<clinit>";

    public static final String javaLangObject = "java/lang/Object";
    public static final String arglessReturnVoid = "()V";

    private ClassWriter createClass(int access, String className, String[] interfaces) {
        return createClass(access, className, javaLangObject, interfaces);
    }

    private ClassWriter createClass(int access, String className, String superType, String[] interfaces) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        writer.visit(Opcodes.V1_8, access, className, null, superType, interfaces);
        return writer;
    }

    @Override
    public Void visit(SimpleNode node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeDeclBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAlgebraicDataTypeDecl node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAlgebraicDataTypeDeclBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTConstructor node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTExistingTypeOrTParameter node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTInterfaceDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeAliasDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTVariableOrFunctionDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTFunctionDeclBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTValueType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTPatternBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTArgVariables node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTGuard node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTEnumDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTEnumElements node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeElement node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTUnitType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeElementBinding node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTJavaType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTFunctionType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTFunctionLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTFunctionLiteralParameters node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTImportStatement node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAlias node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTJavaFQCN node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTExtendsType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeParameters node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeParameter node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeBound node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTModuleDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTModuleBody node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTCompileUnit node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTMethodInvocation node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTArguments node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTArgument node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAssignment node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTLeftHandSide node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTExprNode node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTElementAccess node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTDocumentation node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTModifierStatement node, Object data) throws KaraffeParserException {
        state.setCompileState(CurrentState.CompilerState.MODIFIER);
        return null;
    }

    @Override
    public Void visit(ASTTypeIdentifier node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTVarIdentifier node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAnnotation node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAbstractModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.ABSTRACT);
        return null;
    }

    @Override
    public Void visit(ASTOverrideModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.OVERRIDE);
        return null;
    }

    @Override
    public Void visit(ASTPrivateModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.PRIVATE);
        return null;
    }

    @Override
    public Void visit(ASTPublicModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.PUBLIC);
        return null;
    }

    @Override
    public Void visit(ASTSealedModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.SEALED);
        return null;
    }

    @Override
    public Void visit(ASTLazyModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.LAZY);
        return null;
    }

    @Override
    public Void visit(ASTVariableModifier node, Object data) throws KaraffeParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.VAR);
        return null;
    }

    @Override
    public Void visit(ASTUnaryPlus node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTUnaryBang node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTUnaryMinus node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTStar node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSlash node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTPercent node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSafeDiv node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSafeRem node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTHat node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTPlus node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTMinus node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTEqualTo node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTNotEqualTo node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTGreaterThan node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTLessThan node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTGreaterThanEqualTo node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTLessThanEqualTo node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTComparable node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTNonComparable node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAnd node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTOr node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTBoolLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTIntLiteral node, Object data) throws KaraffeParserException {
        int value = Integer.parseInt(node.jjtGetValue().toString());
        if (value < 128) {
            state.getMethodVisitor().visitIntInsn(Opcodes.BIPUSH, value);
        } else if (value < 32768) {
            state.getMethodVisitor().visitIntInsn(Opcodes.SIPUSH, value);
        } else {
            int a = state.getClassWriter().newConst(value);
            state.getMethodVisitor().visitLdcInsn(a);
        }
        return null;
    }

    @Override
    public Void visit(ASTFloatLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTStringLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTListLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTTupleLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTUndefinedLiteral node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSingleArrow node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAssign node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTClosedRange node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTHalfOpenRange node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTAA node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSA node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTMA node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTDA node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTRA node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTModuleName node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTMethodName node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTExprName node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTReturnType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTSuperType node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Void visit(ASTInterfaces node, Object data) throws KaraffeParserException {
        return null;
    }

    private void stateCheck(CurrentState.CompilerState expected) {
        if (!state.is(expected)) {
            throw new IllegalStateException();
        }
        state.setCompileState(expected);
    }

    @Override
    public Void visit(ASTSP node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTExpressionName node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTCastExpression node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTNewInstance node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTVarArgs node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTFuncAliasDeclaration node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTFuncAliasAfter node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTFuncAliasBefore node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTWildCard node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTSwitchExpr node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTSwitchCase node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTExpressionBinding node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTFuncPattern node, Object data) throws KaraffeParserException {
        return null;
    }

    @Override
    public Object visit(ASTArgVariable node, Object data) throws KaraffeParserException {
        return null;
    }

}
