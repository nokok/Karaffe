package org.karaffe.compiler.frontend.karaffe.ast.modules;

import org.karaffe.compiler.frontend.karaffe.ast.api.ModuleDirective;
import org.karaffe.compiler.frontend.karaffe.ast.names.ModuleName;
import org.karaffe.compiler.frontend.karaffe.ast.names.PackageName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExportsDef implements ModuleDirective {
    private final PackageName packageName;
    private final List<ModuleName> exportsTo;

    public ExportsDef(PackageName packageName) {
        this(packageName, new ArrayList<>(0));
    }

    public ExportsDef(PackageName packageName, List<? extends ModuleName> exportsTo) {
        this.packageName = Objects.requireNonNull(packageName);
        this.exportsTo = new ArrayList<>(exportsTo);
    }

    @Override
    public String toString() {
        String exportTo = String.join(",", this.exportsTo.stream().map(ModuleName::toString).collect(Collectors.toList()));
        String s;
        if (exportTo.isEmpty()) {
            s = "";
        } else {
            s = " to " + exportTo;
        }

        return String.format("exports %s%s", this.packageName, s);
    }
}
