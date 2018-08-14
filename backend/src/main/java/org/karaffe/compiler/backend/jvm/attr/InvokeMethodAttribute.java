package org.karaffe.compiler.backend.jvm.attr;

import net.nokok.azm.Type;
import org.karaffe.compiler.base.attr.Attribute;

import java.lang.reflect.Executable;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Objects;

public class InvokeMethodAttribute extends Attribute {

    private boolean isPrivateMethod;
    private String methodOwner;
    private String methodName;
    private String methodDescriptor;
    private boolean isInterfaceMethod;

    public InvokeMethodAttribute(Class<?> methodOwner, String methodName, List<Class<?>> parameters) {
        this.methodOwner = Type.getInternalName(methodOwner);
        this.methodName = Objects.requireNonNull(methodName);
        this.methodDescriptor = Type.getMethodDescriptor(Type.VOID_TYPE, parameters.stream().map(Type::getInternalName).toArray(Type[]::new));

        try {
            Class[] parameterClasses = parameters.toArray(new Class[]{});
            Executable target;
            if (methodName.equals("<init>") || methodName.equals("<clinit>")) {
                target = methodOwner.getDeclaredConstructor(parameterClasses);
            } else {
                target = methodOwner.getDeclaredMethod(methodName, parameterClasses);
            }
            this.isPrivateMethod = Modifier.isPrivate(target.getModifiers());
            this.isInterfaceMethod = methodOwner.isInterface();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean isPrivateMethod() {
        return this.isPrivateMethod;
    }

    public String getMethodOwner() {
        return this.methodOwner;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getMethodDescriptor() {
        return this.methodDescriptor;
    }

    public boolean isInterfaceMethod() {
        return this.isInterfaceMethod;
    }
}
