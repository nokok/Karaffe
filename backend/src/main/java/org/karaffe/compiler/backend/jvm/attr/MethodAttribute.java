package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.mir.util.attr.Attribute;

import java.lang.reflect.Method;
import java.util.Objects;

public class MethodAttribute extends Attribute {
    private Method method;

    public MethodAttribute(Method method) {
        this.method = Objects.requireNonNull(method);
    }

    public Method getMethodObject() {
        return method;
    }
}
