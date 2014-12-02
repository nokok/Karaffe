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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
import net.nokok.karaffe.parser.util.UnicodeUtil;
import net.nokok.karaffe.parser.util.VariableDeclarationData;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class BytecodeGenerator implements KaraffeParserVisitor {

    private final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    private final Map<String, byte[]> classes = new HashMap<>();
    private final MethodVisitor constructor;
    private final MethodVisitor mainMethodVisitor;

    private final String FILE_NAME;

    public BytecodeGenerator(String fileName) {
        this.FILE_NAME = UnicodeUtil.unicodeToJavaIdentifier(fileName.replace(".krf", ""));

        //public class ファイル名 extends Object
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, this.FILE_NAME, null, "java/lang/Object", null);
        classWriter.visitSource(fileName, null);

        //$TOPLEVEL_CLASS_NAME クラスのコンストラクタの生成
        constructor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructor.visitCode();
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);

        //public static void main(String[] args){
        mainMethodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", Type.getMethodDescriptor(Type.VOID_TYPE, Type.getType(String[].class)), null, null);
    }

    @Override
    public Object visit(ASTCompileUnit node, Object data) throws KaraffeParserException {
        printNode(node);
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTVariableDeclaration node, Object data) throws KaraffeParserException {
        printNode(node);
        VariableDeclarationData vdData = (VariableDeclarationData) node.jjtGetValue();
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, vdData.identifier(), Type.getDescriptor(vdData.type()), null, null);
        return node.childrenAccept(this, data);
    }

    @Override
    public String visit(ASTStringLiteral node, Object data) throws KaraffeParserException {
        printNode(node);
        return (String) node.jjtGetValue();
    }

    public Map<String, byte[]> toByteArrays() {
        constructor.visitInsn(Opcodes.RETURN);
        constructor.visitMaxs(0, 0);
        constructor.visitEnd();
        mainMethodVisitor.visitInsn(Opcodes.RETURN);
        mainMethodVisitor.visitMaxs(0, 0);
        mainMethodVisitor.visitEnd();
        classWriter.visitEnd();
        classes.put(FILE_NAME, classWriter.toByteArray());
        return Collections.unmodifiableMap(classes);
    }

    @Override
    public Object visit(SimpleNode node, Object data) throws KaraffeParserException {
        printNode(node);
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModuleStatement node, Object data) throws KaraffeParserException {
        printNode(node);
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTNewLineToken node, Object data) throws KaraffeParserException {
        printNode(node);
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTModifier node, Object data) throws KaraffeParserException {
        printNode(node);
        return node.childrenAccept(this, data);
    }

    private void printNode(SimpleNode node) throws KaraffeParserException {
        System.out.println(node.jjtGetNumChildren());
    }

    @Override
    public Object visit(ASTComparisonOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAdditiveOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTMultiplyOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTOrOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAndOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTUnaryOpExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTPrimaryExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTAssignExpr node, Object data) throws KaraffeParserException {
        return node.childrenAccept(this, data);
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
}
