package org.karaffe.compiler.base.mir.instructions;

import org.karaffe.compiler.base.attr.Attributes;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.base.tree.Tree;

public interface DeprecatedInstruction extends Attributes {
    InstructionType getInstType();

    Position getPosition();

    void setPosition(Position position);

    Tree getTree();

    void setTree(Tree tree);

}
