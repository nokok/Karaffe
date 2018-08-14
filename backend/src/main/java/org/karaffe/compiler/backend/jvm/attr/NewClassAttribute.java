package org.karaffe.compiler.backend.jvm.attr;

import org.karaffe.compiler.base.attr.Attribute;

import java.util.List;

public class NewClassAttribute extends Attribute implements ModifierAttribute {

    private int modifiers;
    private Class<?> superClass;
    private List<Class<?>> interfaces;

    public NewClassAttribute(int modifiers, Class<?> superClass, List<Class<?>> interfaces) {
        this.modifiers = modifiers;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

    @Override
    public int getModifiers() {
        return this.modifiers;
    }

    public Class<?> getSuperClass() {
        return this.superClass;
    }

    public List<Class<?>> getInterfaces() {
        return this.interfaces;
    }
}
