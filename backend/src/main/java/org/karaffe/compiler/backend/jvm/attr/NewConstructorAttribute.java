package org.karaffe.compiler.backend.jvm.attr;

import java.util.List;

public class NewConstructorAttribute extends NewMethodAttribute {

    public NewConstructorAttribute(int modifiers, List<Class<?>> parameters, List<Class<?>> throwsList) {
        super(modifiers, "<init>", void.class, parameters, throwsList);
    }
}
