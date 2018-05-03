package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.frontend.karaffe.ast.api.TypeDefStatement;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

import java.util.ArrayList;
import java.util.List;

public interface TypeDefInterfacesTransformer extends SimpleNameTransformer {

    public default void onInterfacesBefore(TypeDefStatement parent, List<? extends SimpleName> oldInterfaces) {

    }

    public default void onInterfacesAfter(TypeDefStatement parent, List<? extends SimpleName> oldInterfaces) {

    }

    public default List<? extends SimpleName> transformBodyOnTypeDefInterfaces(TypeDefStatement parent, List<? extends SimpleName> oldInterfaces) {
        List<SimpleName> interfaces = new ArrayList<>(oldInterfaces.size());
        for (SimpleName i : oldInterfaces) {
            interfaces.add(transform(i));
        }
        return interfaces;
    }

    public default List<? extends SimpleName> transformOnTypeDefInterfaces(TypeDefStatement parent, List<? extends SimpleName> oldInterfaces) {
        onInterfacesBefore(parent, oldInterfaces);
        List<? extends SimpleName> interfaces = transformBodyOnTypeDefInterfaces(parent, oldInterfaces);
        onInterfacesAfter(parent, interfaces);
        return interfaces;
    }
}
