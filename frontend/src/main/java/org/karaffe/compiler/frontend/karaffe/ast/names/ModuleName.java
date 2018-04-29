package org.karaffe.compiler.frontend.karaffe.ast.names;

import org.karaffe.compiler.frontend.karaffe.ast.api.AbstractTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModuleName extends AbstractTree {
    private final List<SimpleName> moduleName;

    public ModuleName(String moduleName) {
        this(moduleName.split("\\."));
    }

    public ModuleName(String... moduleNames) {
        this(Arrays.asList(moduleNames).stream().map(SimpleName::new).collect(Collectors.toList()));
    }

    public ModuleName(List<? extends SimpleName> moduleName) {
        this.moduleName = new ArrayList<>(moduleName);
    }

    public List<SimpleName> getModuleNames() {
        return new ArrayList<>(moduleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName.toArray());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ModuleName) {
            ModuleName other = (ModuleName) obj;
            return this.moduleName.equals(other.moduleName);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.join(".", this.moduleName);
    }
}
