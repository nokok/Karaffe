package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.ImmutableCollections;
import org.karaffe.compiler.tree.base.NodeType;

public class Parameters extends AbstractNode implements ImmutableCollections<ValDef> {
    private final List<ValDef> parameters = new ArrayList<>();

    public Parameters() {
        super(NodeType.S_PARAM);
    }

    public Parameters(final ValDef valDef) {
        this();
        this.parameters.add(valDef);
    }

    public Parameters(final List<ValDef> valDefs) {
        this();
        this.parameters.addAll(valDefs);
    }

    @Override
    public void forEach(final Consumer<? super ValDef> action) {
        this.parameters.forEach(action);
    }

    @Override
    public int size() {
        return this.parameters.size();
    }

    @Override
    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.parameters.contains(o);
    }

    @Override
    public Iterator<ValDef> iterator() {
        return this.parameters.iterator();
    }

    @Override
    public ValDef get(final int index) {
        return this.parameters.get(index);
    }

    @Override
    public Stream<ValDef> stream() {
        return this.parameters.stream();
    }

    @Override
    public Stream<ValDef> parallelStream() {
        return this.parameters.parallelStream();
    }

}
