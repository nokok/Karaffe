package org.karaffe.compiler.frontend.karaffe.transformer.api;

import org.karaffe.compiler.ast.api.ModuleDirective;

public interface ModuleDirectiveTransformer {
    public default void onRequireDirectiveBefore(ModuleDirective moduleDirective) {

    }

    public default void onRequireDirectiveAfter(ModuleDirective moduleDirective) {

    }

    public default ModuleDirective transformBody(ModuleDirective moduleDirective) {
        return moduleDirective;
    }

    public default ModuleDirective transform(ModuleDirective moduleDirective) {
        onRequireDirectiveBefore(moduleDirective);
        ModuleDirective afterModuleDirective = transformBody(moduleDirective);
        onRequireDirectiveAfter(afterModuleDirective);
        return afterModuleDirective;
    }
}
