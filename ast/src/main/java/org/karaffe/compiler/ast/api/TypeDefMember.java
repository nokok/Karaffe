package org.karaffe.compiler.ast.api;

import org.karaffe.compiler.ast.names.SimpleName;

public interface TypeDefMember extends Statement {
    public SimpleName getName();
}
