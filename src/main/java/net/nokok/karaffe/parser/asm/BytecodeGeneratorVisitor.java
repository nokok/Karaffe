/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import java.util.ArrayList;
import java.util.List;
import net.nokok.karaffe.parser.ASTAmbiguousName;
import net.nokok.karaffe.parser.ASTAssignmentOp;
import net.nokok.karaffe.parser.ASTBinaryIntLiteral;
import net.nokok.karaffe.parser.ASTBoolLiteral;
import net.nokok.karaffe.parser.ASTCharLiteral;
import net.nokok.karaffe.parser.ASTClassDecl;
import net.nokok.karaffe.parser.ASTDecimalFPLiteral;
import net.nokok.karaffe.parser.ASTDecimalIntLiteral;
import net.nokok.karaffe.parser.ASTDictionaryDisplay;
import net.nokok.karaffe.parser.ASTDictionaryPair;
import net.nokok.karaffe.parser.ASTExpression;
import net.nokok.karaffe.parser.ASTExpressionName;
import net.nokok.karaffe.parser.ASTFloatLiteral;
import net.nokok.karaffe.parser.ASTFuncLiteralArg;
import net.nokok.karaffe.parser.ASTFunctionLiteral;
import net.nokok.karaffe.parser.ASTHexIntLiteral;
import net.nokok.karaffe.parser.ASTHexadecimalFPLiteral;
import net.nokok.karaffe.parser.ASTIdentifierTypeBinding;
import net.nokok.karaffe.parser.ASTIfBody;
import net.nokok.karaffe.parser.ASTIfExpr;
import net.nokok.karaffe.parser.ASTLeftHandSide;
import net.nokok.karaffe.parser.ASTListDisplay;
import net.nokok.karaffe.parser.ASTMethodInvocation;
import net.nokok.karaffe.parser.ASTMethodName;
import net.nokok.karaffe.parser.ASTNullLiteral;
import net.nokok.karaffe.parser.ASTOctIntLiteral;
import net.nokok.karaffe.parser.ASTPackageDecl;
import net.nokok.karaffe.parser.ASTStringLiteral;
import net.nokok.karaffe.parser.ASTTupleDisplay;
import net.nokok.karaffe.parser.ASTUndefinedLiteral;
import net.nokok.karaffe.parser.ASTWhileLoopExpr;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.asm.typechecker.ClassResolver;
import net.nokok.karaffe.parser.excptn.ParserException;
import net.nokok.karaffe.parser.util.AmbiguousName;
import net.nokok.karaffe.parser.util.CurrentState;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BytecodeGeneratorVisitor extends ParserDefaultVisitor {

    private final CurrentState state = new CurrentState();

    public static final String CTOR_NAME = "<init>";
    public static final String CLASS_INITIALIZER = "<clinit>";

    public static final String javaLangObject = "java/lang/Object";
    public static final String arglessReturnVoid = "()V";

    private String currentPackage = ".";

    public static final ClassResolver availableTypes = new ClassResolver();

    private ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    private MethodVisitor currentMethodVisitor = null;
    private MethodVisitor ctorVisitor = null;

    private List<Runnable> ctorRunLater = new ArrayList<>();

    private ClassWriter createClass(int access, String className, String[] interfaces) {
        return createClass(access, className, javaLangObject, interfaces);
    }

    private ClassWriter createClass(int access, String className, String superType, String[] interfaces) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        writer.visit(Opcodes.V1_8, access, className, null, superType, interfaces);
        return writer;
    }

    @Override
    public Object visit(ASTPackageDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        AmbiguousName util = new AmbiguousName(node);
        currentPackage = util.getPath();
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTClassDecl node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
//        KrfClassNode classNode = new KrfClassNode(availableTypes, node);
//        createClass(classNode.getModifier(), classNode.getClassName(), classNode.getSuperType(), classNode.getInterfaces());
        ctorVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        ctorRunLater.forEach(Runnable::run);
        return null;
    }

    //------------------------------------------------------------------------------------
    //Expression
    //------------------------------------------------------------------------------------
    @Override
    public Object visit(ASTExpression node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTNullLiteral node, Object data) throws ParserException {
        currentMethodVisitor.visitInsn(Opcodes.ACONST_NULL);
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
        boolean bool = Boolean.parseBoolean(node.jjtGetValue().toString());
        if (bool) {
            currentMethodVisitor.visitInsn(Opcodes.ICONST_1);
        } else {
            currentMethodVisitor.visitInsn(Opcodes.ICONST_0);
        }
        return null;
    }

    @Override
    public Object visit(ASTCharLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        node.childrenAccept(this, data);
        return null;
    }

    @Override
    public Object visit(ASTStringLiteral node, Object data) throws net.nokok.karaffe.parser.excptn.ParserException {
        currentMethodVisitor.visitLdcInsn(node.jjtGetValue().toString());
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
}
