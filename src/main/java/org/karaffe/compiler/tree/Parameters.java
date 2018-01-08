package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.tree.visitor.KaraffeTreeVisitor;

public class Parameters extends AbstractNodes {

    public Parameters() {
        this(new ArrayList<>(0));
    }

    public Parameters(final Node valDef) {
        this(new ArrayList<>(Arrays.asList(valDef)));
    }

    public Parameters(final List<Node> valDefs) {
        super(NodeType.S_PARAM, valDefs);
    }

    @Override
    public void accept(KaraffeTreeVisitor visitor) {
        visitor.visit(this);
    }
}
