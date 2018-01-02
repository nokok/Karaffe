package org.karaffe.compiler.tree;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface ImmutableCollections<T> {

    void forEach(Consumer<? super T> action);

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<? extends T> iterator();

    T get(int index);

    Stream<? extends T> stream();

    Stream<? extends T> parallelStream();

}