package org.karaffe.compiler.util.resolver;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

import java.lang.reflect.Method;
import java.util.Objects;

public class OperatorResolver {
  private final Class<?> ownerClass;

  public OperatorResolver(Class<?> ownerClass) {
    this.ownerClass = Objects.requireNonNull(ownerClass);
  }

  public AbstractInsnNode plus(Class<?> param) {
    if (this.ownerClass.isPrimitive()) {
      if (this.ownerClass.equals(double.class) || param.equals(double.class)) {
        return new InsnNode(Opcodes.DADD);
      } else if (this.ownerClass.equals(float.class) || param.equals(float.class)) {
        return new InsnNode(Opcodes.FADD);
      } else if (this.ownerClass.equals(long.class) || param.equals(long.class)) {
        return new InsnNode(Opcodes.LADD);
      }
      return new InsnNode(Opcodes.IADD);
    }
    MethodResolver methodResolver = new MethodResolver(ownerClass);
    Method plusMethod = methodResolver.getMethod("plus", param).orElseThrow(IllegalStateException::new);
    return new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(this.ownerClass), "plus", Type.getMethodDescriptor(plusMethod), plusMethod.getDeclaringClass().isInterface());
  }

  public AbstractInsnNode minus(Class<?> param) {
    if (this.ownerClass.isPrimitive()) {
      if (this.ownerClass.equals(double.class) || param.equals(double.class)) {
        return new InsnNode(Opcodes.DSUB);
      } else if (this.ownerClass.equals(float.class) || param.equals(float.class)) {
        return new InsnNode(Opcodes.FSUB);
      } else if (this.ownerClass.equals(long.class) || param.equals(long.class)) {
        return new InsnNode(Opcodes.LSUB);
      }
      return new InsnNode(Opcodes.ISUB);
    }
    MethodResolver methodResolver = new MethodResolver(ownerClass);
    Method plusMethod = methodResolver.getMethod("minus", param).orElseThrow(IllegalStateException::new);
    return new MethodInsnNode(Opcodes.INVOKEVIRTUAL, Type.getInternalName(this.ownerClass), "minus", Type.getMethodDescriptor(plusMethod), plusMethod.getDeclaringClass().isInterface());
  }
}
