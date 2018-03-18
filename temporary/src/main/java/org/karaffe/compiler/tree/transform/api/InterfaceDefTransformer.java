package org.karaffe.compiler.tree.transform.api;

import java.util.stream.Collectors;

import org.karaffe.compiler.ast.api.TypeDefStatement;
import org.karaffe.compiler.ast.statements.InterfaceDef;

public interface InterfaceDefTransformer extends TypeDefMemberTransformer {

    public default void onInterfaceDefBefore(InterfaceDef interfaceDef) {

    }

    public default void onInterfaceDefAfter(InterfaceDef interfaceDef) {

    }

    public default TypeDefStatement transform(InterfaceDef oldInterfaceDef) {
        onInterfaceDefBefore(oldInterfaceDef);
        InterfaceDef interfaceDef = transformBody(oldInterfaceDef);
        onInterfaceDefAfter(interfaceDef);
        return interfaceDef;
    }

    public default InterfaceDef transformBody(InterfaceDef oldInterfaceDef) {
        return new InterfaceDef(
                oldInterfaceDef.getPosition(),
                transform(oldInterfaceDef.getName()),
                oldInterfaceDef.getBody().stream().map(this::transform).collect(Collectors.toList()));
    }
}
