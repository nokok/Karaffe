package org.karaffe.compiler.gen;

import karaffe.core.Int;
import org.karaffe.compiler.resolver.ConstructorResolver;
import org.karaffe.compiler.resolver.OperatorResolver;
import org.karaffe.compiler.util.BytecodeEntry;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class BytecodeSupport {

    private ClassWriter classWriter = null;
    private String currentFileName = null;
    private MethodVisitor methodVisitor = null;
    private String currentClassName = null;

    public BytecodeSupport() {
        this.classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    }

    public void newClassDefinition(String className, String sourceFileName) {
        this.currentClassName = Objects.requireNonNull(className);
        this.currentFileName = Objects.requireNonNull(sourceFileName);
        this.classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, Type.getInternalName(Object.class), null);
        this.classWriter.visitSource(sourceFileName, null);
    }

    public <T> void newFieldDefinition(String fieldName, Class<T> type) {
        this.classWriter.visitField(Opcodes.ACC_PUBLIC, fieldName, Type.getDescriptor(type), null, null);
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
        Type[] t = parameterMap.values().stream().map(Type::getType).toArray(Type[]::new);
        methodVisitor = classWriter.visitMethod(
                access,
                methodName,
                Type.getMethodDescriptor(Type.getType(returnType), t),
                null,
                null
        );
    }

    public void endMethod() {
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    public void pushIntLiteral(int i) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(Int.class));
        methodVisitor.visitInsn(Opcodes.DUP);
        ConstructorResolver resolver = new ConstructorResolver(Int.class);
        Constructor<?> constructor = resolver.getConstructor(int.class).orElseThrow(IllegalStateException::new);
        AbstractInsnNode abstractInsnNode = BytecodeSelectorForNumber.fromInt(i);
        abstractInsnNode.accept(methodVisitor);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(Int.class), "<init>", Type.getConstructorDescriptor(constructor), false);
    }

    public void pushStringLiteral(String value) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, Type.getInternalName(karaffe.core.String.class));
        methodVisitor.visitInsn(Opcodes.DUP);
        ConstructorResolver resolver = new ConstructorResolver(karaffe.core.String.class);
        Constructor<?> constructor = resolver.getConstructor(java.lang.String.class).orElseThrow(IllegalStateException::new);
        methodVisitor.visitLdcInsn(value);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, Type.getInternalName(karaffe.core.String.class), "<init>", Type.getConstructorDescriptor(constructor), false);
    }

    public void invokeInstanceMethod(Method method) {
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(method.getDeclaringClass()),
                Objects.requireNonNull(method.getName()),
                Type.getMethodDescriptor(method),
                method.getDeclaringClass().isInterface()
        );
    }

    public void invokeStaticMethod(Method method) {
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                Type.getInternalName(method.getDeclaringClass()),
                Objects.requireNonNull(method.getName()),
                Type.getMethodDescriptor(method),
                method.getDeclaringClass().isInterface()
        );
    }

    public void applyPlusOperator(Class<?> owner, Class<?> param) {
        OperatorResolver resolver = new OperatorResolver(owner);
        AbstractInsnNode plus = resolver.plus(param);
        plus.accept(methodVisitor);
    }

    public void applyMinusOperator(Class<?> owner, Class<?> param) {
        OperatorResolver resolver = new OperatorResolver(owner);
        AbstractInsnNode plus = resolver.minus(param);
        plus.accept(methodVisitor);
    }
}
