package org.karaffe.compiler.tree;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface ImmutableCollections<T> {

    boolean contains(Object o);

    void forEach(Consumer<? super T> action);

    T get(int index);

    boolean isEmpty();

    Iterable<? extends T> iterable();

    Iterator<? extends T> iterator();

    Stream<? extends T> parallelStream();

    int size();

    Stream<? extends T> stream();

}