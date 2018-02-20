package org.karaffe.compiler.tree.v2.names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.karaffe.compiler.pos.Position;
import org.karaffe.compiler.tree.v2.api.AbstractTree;

public class FullyQualifiedName extends AbstractTree {
    private final List<? extends SimpleName> names;

    public FullyQualifiedName(List<? extends SimpleName> names) {
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedName(SimpleName... names) {
        this(new ArrayList<>(Arrays.asList(names)));
    }

    public FullyQualifiedName(Position position, List<? extends SimpleName> names) {
        super(position);
        this.names = new ArrayList<>(names);
    }

    public FullyQualifiedName(Position position, SimpleName... names) {
        this(position, new ArrayList<>(Arrays.asList(names)));
    }

    @Override
    public String toString() {
        return String.join(".", this.names);
    }

}
