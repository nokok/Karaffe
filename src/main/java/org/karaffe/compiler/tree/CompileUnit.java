package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.NodeType;

public class CompileUnit extends AbstractNode {
    private final PackageDef packageDef;
    private final TypeDefs typeDefs;

    public CompileUnit(final PackageDef packageDecl, final TypeDefs classes) {
        super(NodeType.COMPILEUNIT);
        this.packageDef = packageDecl;
        this.typeDefs = classes;
    }

    @Override
    public String toString() {
        return String.format("(CompileUnit %s %s)", this.packageDef, this.typeDefs);
    }

}
