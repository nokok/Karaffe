package org.karaffe.compiler.gen;

import net.nokok.azm.ClassWriter;
import net.nokok.azm.MethodVisitor;
import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import net.nokok.azm.tree.AbstractInsnNode;
import org.karaffe.compiler.util.BytecodeEntry;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class BytecodeSupport {

    private ClassWriter classWriter = null;
    private MethodVisitor methodVisitor = null;
    private String currentClassName = null;

    public BytecodeSupport() {
        this.classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    }

    public void newClassDefinition(String className) {
        this.currentClassName = Objects.requireNonNull(className);
        this.classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, Type.getInternalName(Object.class), null);
    }

    public BytecodeEntry closeThisClass() {
        this.classWriter.visitEnd();
        byte[] byteCode = this.classWriter.toByteArray();
        return new BytecodeEntry(Paths.get(this.currentClassName + ".class"), byteCode);
    }

    public void startMainMethod() {
        if (this.methodVisitor != null) {
            this.methodVisitor.visitInsn(Opcodes.RETURN);
            endMethod();
        }
        startMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, void.class, "main", Collections.singletonMap("args", String[].class));
    }

    public void startMethod(int access, Class<?> returnType, String methodName, Map<String, Class<?>> parameterMap) {
        if (this.methodVisitor != null) {
            endMethod();
        }
        Type[] t = (Type[]) parameterMap.values().stream().map(Type::getInternalName).toArray();
        methodVisitor = classWriter.visitMethod(
                access,
                methodName,
                Type.getMethodDescriptor(Type.getType(returnType), t),
                null,
                null
        );
    }

    public void endMethod() {
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    public void pushIntLiteral(int i) {
        AbstractInsnNode abstractInsnNode = BytecodeSelectorForNumber.fromInt(i);
        abstractInsnNode.accept(methodVisitor);
    }

    public void pushStringLiteral(String value) {
        methodVisitor.visitLdcInsn(value);
    }
}
