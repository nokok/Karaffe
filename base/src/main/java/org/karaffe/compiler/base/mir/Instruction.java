package org.karaffe.compiler.base.mir;

import org.karaffe.compiler.base.attr.Attributes;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;

public interface Instruction extends Attributes {
    InstructionType getInstType();

    Position getPosition();

    void setPosition(Position position);

    Tree getTree();

    void setTree(Tree tree);

}
