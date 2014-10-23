package net.nokok.karaffe.javacc;

import java.util.Map;

public class FunctionType {

    private final Map<Name, Type> beforeTypes;
    private final Type afterType;

    public FunctionType(Map<Name, Type> beforeTypes, Type afterType) {
        this.beforeTypes = beforeTypes;
        this.afterType = afterType;
    }

}
