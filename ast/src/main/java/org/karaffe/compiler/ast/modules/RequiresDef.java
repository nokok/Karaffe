package org.karaffe.compiler.ast.modules;

import org.karaffe.compiler.ast.api.ModuleDirective;
import org.karaffe.compiler.ast.names.ModuleName;

import java.util.Objects;

public class RequiresDef implements ModuleDirective {

    private final ModuleName moduleName;
    private final boolean isTransitive;
    private final boolean isStatic;

    public RequiresDef(ModuleName moduleName) {
        this(moduleName, false, false);
    }

    public RequiresDef(ModuleName moduleName, boolean isTransitive, boolean isStatic) {
        this.moduleName = Objects.requireNonNull(moduleName);
        this.isTransitive = isTransitive;
        this.isStatic = isStatic;
    }

    @Override
    public String toString() {
        return String.format("requires%s%s %s",
                this.isTransitive ? " transitive" : "",
                this.isStatic ? " static" : "",
                this.moduleName
        );
    }
}
