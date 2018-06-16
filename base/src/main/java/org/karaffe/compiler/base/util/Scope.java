package org.karaffe.compiler.base.util;

import org.karaffe.compiler.base.tree.Tree;
import org.karaffe.compiler.base.tree.def.SimpleImport;
import org.karaffe.compiler.base.tree.term.Path;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final Scope parent;
    private final Map<Tree/*TypeName*/, Tree/*FullyQualifiedTypeNames*/> importRenamingRule;

    public Scope() {
        this(null);
    }

    public Scope(Scope parent) {
        this.parent = parent;
        this.importRenamingRule = new HashMap<>();
    }

    public void onImportDef(SimpleImport importDef) {
        Path name = importDef.getName();
    }

    public Scope newScope() {
        return new Scope(this);
    }

    public Path resolveFQCN(Path target) {
        Tree tree = importRenamingRule.get(target);


        if (this.parent != null) {
            return this.parent.resolveFQCN(target);
        } else {
            return null;
        }
    }
}
