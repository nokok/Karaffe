package org.karaffe.compiler.tree.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.ImmutableCollections;
import org.karaffe.compiler.tree.NodeType;

public class AbstractNodes<T extends Node> extends AbstractNode implements ImmutableCollections<T> {

    public AbstractNodes(final NodeType nodeType) {
        this(nodeType, new ArrayList<>());
    }

    public AbstractNodes(final NodeType nodeType, final T node) {
        this(nodeType, new ArrayList<>(Arrays.asList(node)));
    }

    public AbstractNodes(final NodeType nodeType, final List<T> nodes) {
        super(nodeType, nodes);
    }

    @Override
    public void forEach(final Consumer<? super T> action) {
        ((List<T>) this.getChildren()).forEach(action);
    }

    @Override
    public int size() {
        return ((List<T>) this.getChildren()).size();
    }

    @Override
    public boolean isEmpty() {
        return ((List<T>) this.getChildren()).isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return ((List<T>) this.getChildren()).contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return ((List<T>) this.getChildren()).iterator();
    }

    @Override
    public T get(final int index) {
        return ((List<T>) this.getChildren()).get(index);
    }

    @Override
    public Stream<T> stream() {
        return ((List<T>) this.getChildren()).stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return ((List<T>) this.getChildren()).parallelStream();
    }

}
