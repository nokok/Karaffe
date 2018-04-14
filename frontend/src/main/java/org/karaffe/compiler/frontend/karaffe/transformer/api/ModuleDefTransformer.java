package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.ModuleDef;

import static java.util.stream.Collectors.toList;

public interface ModuleDefTransformer extends ModuleNameTransformer, PackageDefTransformer, ModuleDirectiveTransformer {

    public default void onModuleDefBefore(ModuleDef moduleDef) {

    }

    public default void onModuleDefAfter(ModuleDef moduleDef) {

    }

    public default ModuleDef transformBody(ModuleDef oldModuleDef) {
        return new ModuleDef(
                transform(oldModuleDef.getModuleName()),
                oldModuleDef.getPackages().stream().map(this::transform).collect(toList()),
                oldModuleDef.getDirectives().stream().map(this::transform).collect(toList())
        );
    }

    public default ModuleDef transform(ModuleDef moduleDef) {
        onModuleDefBefore(moduleDef);
        ModuleDef afterModuleDef = transformBody(moduleDef);
        onModuleDefAfter(afterModuleDef);
        return afterModuleDef;
    }
}
