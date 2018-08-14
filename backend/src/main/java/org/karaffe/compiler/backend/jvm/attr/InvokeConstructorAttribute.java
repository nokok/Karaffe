package org.karaffe.compiler.backend.jvm.attr;

import net.nokok.azm.Type;
import org.karaffe.compiler.base.attr.Attribute;

import java.lang.reflect.Constructor;
import java.util.List;

public class InvokeConstructorAttribute extends Attribute {
    private final Class<?> constructorOwner;
    private final List<Class<?>> parameters;

    public InvokeConstructorAttribute(Class<?> constructorOwner, List<Class<?>> parameters) {
        this.constructorOwner = constructorOwner;
        this.parameters = parameters;
    }

    public String getConstructorOwner() {
        return Type.getInternalName(this.constructorOwner);
    }

    public String getDescriptor() {
        try {
            Constructor<?> constructor = constructorOwner.getConstructor(parameters.toArray(new Class[]{}));
            return Type.getConstructorDescriptor(constructor);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }
}
