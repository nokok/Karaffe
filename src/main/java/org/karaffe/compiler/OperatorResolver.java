package org.karaffe.compiler;

import net.nokok.azm.Opcodes;
import net.nokok.azm.Type;
import net.nokok.azm.tree.AbstractInsnNode;
import net.nokok.azm.tree.InsnNode;
import net.nokok.azm.tree.MethodInsnNode;

import java.lang.reflect.Method;
import java.util.Objects;

public class OperatorResolver {
    private final Class<?> ownerClass;

    public OperatorResolver(Class<?> ownerClass) {
        this.ownerClass = Objects.requireNonNull(ownerClass);
        if (!new Klass(this.ownerClass).isArithmeticOperatorApplicable()) {
            throw new IllegalArgumentException();
        }
    }

    public AbstractInsnNode plus(Class<?> param) {
        if (this.ownerClass.isPrimitive()) {
            if (this.ownerClass.equals(double.class)) {
                return new InsnNode(Opcodes.DADD);
            } else if (this.ownerClass.equals(float.class)) {
                return new InsnNode(Opcodes.FADD);
            } else if (this.ownerClass.equals(long.class)) {
                return new InsnNode(Opcodes.LADD);
            }
            return new InsnNode(Opcodes.IADD);
        }
        MethodResolver methodResolver = new MethodResolver(ownerClass);
        Method plusMethod = methodResolver.getMethod("plus", param).orElseThrow(IllegalStateException::new);
        return new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(this.ownerClass), "plus", Type.getMethodDescriptor(plusMethod), plusMethod.getDeclaringClass().isInterface());
    }
}
