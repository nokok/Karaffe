package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.tree.base.AbstractNodes;

public class MethodDefs extends AbstractNodes<MethodDef> {

    public MethodDefs() {
        this(new ArrayList<>(0));
    }

    public MethodDefs(final MethodDef method) {
        this(new ArrayList<>(Arrays.asList(method)));
    }

    public MethodDefs(final MethodDef... modifiers) {
        this(new ArrayList<>(Arrays.asList(modifiers)));
    }

    public MethodDefs(final List<MethodDef> modifiers) {
        super(NodeType.S_METHODDEF, modifiers);
    }

}
