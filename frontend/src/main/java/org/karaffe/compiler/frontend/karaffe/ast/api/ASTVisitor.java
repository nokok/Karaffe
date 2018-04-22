package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Block;

public interface ASTVisitor {

    void visit(Apply n);

    void visit(Block block);
}
