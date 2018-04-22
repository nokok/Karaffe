package org.karaffe.compiler.frontend.karaffe.ast.api;

import org.karaffe.compiler.frontend.karaffe.ast.expressions.Apply;
import org.karaffe.compiler.frontend.karaffe.ast.expressions.Block;

public interface ASTVisitor {

    public void visit(Apply n);

    public void visit(Block block);
}
