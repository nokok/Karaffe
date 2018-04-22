package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

@Deprecated
public interface TypeDefMember extends Statement {
    public SimpleName getName();
}
