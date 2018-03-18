package org.karaffe.compiler.ast.api;

import org.karaffe.compiler.ast.names.SimpleName;

public interface ImportStatement extends Statement {
    public SimpleName getImportedSimpleName();
}
