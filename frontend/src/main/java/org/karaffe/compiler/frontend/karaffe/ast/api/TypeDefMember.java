package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

public interface TypeDefMember extends Statement {
    SimpleName getName();
}
