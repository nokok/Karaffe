package net.nokok.karaffe.javacc;

import net.nokok.karaffe.javacc.identifier.TypeId;
import java.util.Map;

public class FunctionType {

    private final Map<Name, TypeId> beforeTypes;
    private final TypeId afterType;

    public FunctionType(Map<Name, TypeId> beforeTypes, TypeId afterType) {
        this.beforeTypes = beforeTypes;
        this.afterType = afterType;
    }

}
