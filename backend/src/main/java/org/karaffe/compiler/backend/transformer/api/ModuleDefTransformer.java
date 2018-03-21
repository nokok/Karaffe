package org.karaffe.compiler.backend.transformer.api;

import org.karaffe.compiler.ast.ModuleDef;

import static java.util.stream.Collectors.toList;

public interface ModuleDefTransformer extends ModuleNameTransformer, PackageDefTransformer {

    public default void onModuleDefBefore(ModuleDef moduleDef) {

    }

    public default void onModuleDefAfter(ModuleDef moduleDef) {

    }

    public default ModuleDef transformBody(ModuleDef oldModuleDef) {
        return new ModuleDef(
                transform(oldModuleDef.getModuleName()),
                oldModuleDef.getPackages().stream().map(this::transform).collect(toList()),
                oldModuleDef.getExports().stream().map(this::transform).collect(toList()),
                oldModuleDef.getRequires().stream().map(this::transform).collect(toList())
        );
    }

    public default ModuleDef transform(ModuleDef moduleDef) {
        onModuleDefBefore(moduleDef);
        ModuleDef afterModuleDef = transformBody(moduleDef);
        onModuleDefAfter(afterModuleDef);
        return afterModuleDef;
    }
}
