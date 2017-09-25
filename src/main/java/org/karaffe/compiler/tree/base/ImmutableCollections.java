package org.karaffe.compiler.tree.base;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface ImmutableCollections<T> {

    void forEach(Consumer<? super T> action);

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<T> iterator();

    T get(int index);

    Stream<T> stream();

    Stream<T> parallelStream();

}