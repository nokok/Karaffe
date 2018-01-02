package org.karaffe.compiler.tree.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.ImmutableCollections;
import org.karaffe.compiler.tree.NodeType;

public class AbstractNodes extends AbstractNode implements ImmutableCollections<Node> {

    public AbstractNodes(final NodeType nodeType) {
        this(nodeType, new ArrayList<>());
    }

    public AbstractNodes(final NodeType nodeType, final Node node) {
        this(nodeType, new ArrayList<>(Arrays.asList(node)));
    }

    public AbstractNodes(final NodeType nodeType, final List<Node> nodes) {
        super(nodeType, nodes);
    }

    @Override
    public void forEach(final Consumer<? super Node> action) {
        this.getChildren().forEach(action);
    }

    @Override
    public int size() {
        return this.getChildren().size();
    }

    @Override
    public boolean isEmpty() {
        return this.getChildren().isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.getChildren().contains(o);
    }

    @Override
    public Iterator<? extends Node> iterator() {
        return this.getChildren().iterator();
    }

    @Override
    public Node get(final int index) {
        return this.getChildren().get(index);
    }

    @Override
    public Stream<? extends Node> stream() {
        return this.getChildren().stream();
    }

    @Override
    public Stream<? extends Node> parallelStream() {
        return this.getChildren().parallelStream();
    }

    @Override
    public AbstractNodes normalize() {
        return this;
    }
}
