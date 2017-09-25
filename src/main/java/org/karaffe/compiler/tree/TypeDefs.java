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

public class TypeDefs extends AbstractNode implements ImmutableCollections<TypeDef> {
    private final List<TypeDef> typeDefs;

    public TypeDefs() {
        this(new ArrayList<>());
    }

    public TypeDefs(final TypeDef def) {
        this(new ArrayList<>(Arrays.asList(def)));
    }

    public TypeDefs(final List<TypeDef> defs) {
        super(NodeType.S_TYPEDEF);
        this.typeDefs = defs;
    }

    @Override
    public void forEach(final Consumer<? super TypeDef> action) {
        this.typeDefs.forEach(action);
    }

    @Override
    public int size() {
        return this.typeDefs.size();
    }

    @Override
    public boolean isEmpty() {
        return this.typeDefs.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.typeDefs.contains(o);
    }

    @Override
    public Iterator<TypeDef> iterator() {
        return this.typeDefs.iterator();
    }

    @Override
    public TypeDef get(final int index) {
        return this.typeDefs.get(index);
    }

    @Override
    public Stream<TypeDef> stream() {
        return this.typeDefs.stream();
    }

    @Override
    public Stream<TypeDef> parallelStream() {
        return this.typeDefs.parallelStream();
    }

}
