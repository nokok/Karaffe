package org.karaffe.compiler.tree.v2.api;

import org.karaffe.compiler.tree.v2.names.SimpleName;

public interface ImportStatement extends Statement {
    public SimpleName getImportedSimpleName();
}
