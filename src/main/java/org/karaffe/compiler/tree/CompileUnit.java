package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;

import org.karaffe.compiler.tree.base.AbstractNode;

public class CompileUnit extends AbstractNode {
    public CompileUnit(final PackageDef packageDecl, final TypeDefs classes) {
        super(NodeType.COMPILEUNIT, new ArrayList<>(Arrays.asList(packageDecl, classes)));
    }

}
