package org.karaffe.compiler.tree;

import org.karaffe.compiler.tree.base.NodeD;
import org.karaffe.compiler.util.Position;

public class ClassDecl implements NodeD {
    private final String className;

    public ClassDecl(final String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

    @Override
    public String toString() {
        return String.format("(ClassDecl %s %s)", Position.noPos(), this.className);
    }

}
