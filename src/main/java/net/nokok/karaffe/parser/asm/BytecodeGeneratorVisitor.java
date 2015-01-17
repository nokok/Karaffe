/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import jdk.nashorn.internal.runtime.ParserException;
import net.nokok.karaffe.parser.ASTAlgebraicDataTypeDecl;
import net.nokok.karaffe.parser.ASTArgument;
import net.nokok.karaffe.parser.ASTArguments;
import net.nokok.karaffe.parser.ASTCompileUnit;
import net.nokok.karaffe.parser.ASTInterfaces;
import net.nokok.karaffe.parser.ASTReturnType;
import net.nokok.karaffe.parser.ASTSuperType;
import net.nokok.karaffe.parser.ASTTypeBound;
import net.nokok.karaffe.parser.ASTTypeParameter;
import net.nokok.karaffe.parser.ASTTypeParameters;
import net.nokok.karaffe.parser.ASTVariableModifier;
import net.nokok.karaffe.parser.ParserDefaultVisitor;
import net.nokok.karaffe.parser.SimpleNode;
import net.nokok.karaffe.parser.util.CurrentState;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class BytecodeGeneratorVisitor extends ParserDefaultVisitor {

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
    public Void visit(SimpleNode node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTAlgebraicDataTypeDecl node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeParameters node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeParameter node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTTypeBound node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTCompileUnit node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTArguments node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTArgument node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTVariableModifier node, Object data) throws ParserException {
        stateCheck(CurrentState.CompilerState.MODIFIER);
        state.addModifier(CurrentState.Modifier.VAR);
        return null;
    }
//
//    @Override
//    public Void visit(ASTIntLiteral node, Object data) throws ParserException {
//        int value = Integer.parseInt(node.jjtGetValue().toString());
//        if (value <= Byte.MAX_VALUE) {
//            state.getMethodVisitor().visitIntInsn(Opcodes.BIPUSH, value);
//        } else if (value <= Short.MAX_VALUE) {
//            state.getMethodVisitor().visitIntInsn(Opcodes.SIPUSH, value);
//        } else {
//            int a = state.getClassWriter().newConst(value);
//            state.getMethodVisitor().visitLdcInsn(a);
//        }
//        return null;
//    }

    @Override
    public Void visit(ASTReturnType node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTSuperType node, Object data) throws ParserException {
        return null;
    }

    @Override
    public Void visit(ASTInterfaces node, Object data) throws ParserException {
        return null;
    }

    private void stateCheck(CurrentState.CompilerState expected) {
        if (!state.is(expected)) {
            throw new IllegalStateException();
        }
        state.setCompileState(expected);
    }

}
