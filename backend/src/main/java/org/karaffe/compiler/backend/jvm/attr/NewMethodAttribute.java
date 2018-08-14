package org.karaffe.compiler.backend.jvm.attr;

import net.nokok.azm.Type;
import org.karaffe.compiler.base.attr.Attribute;

import java.util.List;
import java.util.Objects;

public class NewMethodAttribute extends Attribute implements ModifierAttribute, ThrowsElement {

    private int modifiers;
    private String name;
    private Class<?> returnType;
    private List<Class<?>> parameters;
    private List<Class<?>> throwsList;

    public NewMethodAttribute(int modifiers, String name, Class<?> returnType, List<Class<?>> parameters, List<Class<?>> throwsList) {
        this.modifiers = modifiers;
        this.name = Objects.requireNonNull(name);
        this.returnType = Objects.requireNonNull(returnType);
        this.parameters = Objects.requireNonNull(parameters);
        this.throwsList = Objects.requireNonNull(throwsList);
    }

    @Override
    public int getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getReturnType() {
        return this.returnType;
    }

    public Type[] getParameters() {
        return this.parameters.stream().map(Type::getInternalName).toArray(Type[]::new);
    }

    @Override
    public List<Class<?>> getThrows() {
        return this.throwsList;
    }
}
