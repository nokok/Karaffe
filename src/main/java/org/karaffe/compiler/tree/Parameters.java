package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;

public class Parameters extends AbstractNodes<ValDef> {

    public Parameters(final ValDef valDef) {
        this(new ArrayList<>(Arrays.asList(valDef)));
    }

    public Parameters(final List<ValDef> valDefs) {
        super(NodeType.S_PARAM, valDefs);
    }

}
