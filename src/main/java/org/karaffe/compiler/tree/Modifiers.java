package org.karaffe.compiler.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.karaffe.compiler.tree.base.AbstractNode;
import org.karaffe.compiler.tree.base.ImmutableCollections;
import org.karaffe.compiler.tree.base.NodeType;

public class Modifiers extends AbstractNode implements ImmutableCollections<Modifier> {

    private final List<Modifier> modifiers;

    public Modifiers() {
        this(new ArrayList<>(0));
    }

    public Modifiers(final Modifier modifier) {
        this(new ArrayList<>(Arrays.asList(modifier)));
    }

    public Modifiers(final List<Modifier> modifiers) {
        super(NodeType.S_MODIFIER);
        this.modifiers = modifiers;
    }

    @Override
    public void forEach(final Consumer<? super Modifier> action) {
        this.modifiers.forEach(action);
    }

    @Override
    public int size() {
        return this.modifiers.size();
    }

    @Override
    public boolean isEmpty() {
        return this.modifiers.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.modifiers.contains(o);
    }

    @Override
    public Iterator<Modifier> iterator() {
        return this.modifiers.iterator();
    }

    @Override
    public Modifier get(final int index) {
        return this.modifiers.get(index);
    }

    @Override
    public Stream<Modifier> stream() {
        return this.modifiers.stream();
    }

    @Override
    public Stream<Modifier> parallelStream() {
        return this.modifiers.parallelStream();
    }

}
