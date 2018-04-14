package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.names.ModuleName;

public interface ModuleNameTransformer {
    public default void onModuleNameBefore(ModuleName moduleDef) {

    }

    public default void onModuleNameAfter(ModuleName moduleDef) {

    }

    public default ModuleName transformBody(ModuleName oldModuleName) {
        return new ModuleName(oldModuleName.getModuleNames());
    }

    public default ModuleName transform(ModuleName moduleDef) {
        onModuleNameBefore(moduleDef);
        ModuleName afterModuleName = transformBody(moduleDef);
        onModuleNameAfter(afterModuleName);
        return afterModuleName;
    }
}
