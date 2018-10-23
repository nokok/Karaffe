package org.karaffe.compiler.base.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

public class TreeList implements List<Tree> {

    private List<Tree> trees = new ArrayList<>();

    @Override
    public int size() {
        return trees.size();
    }

    @Override
    public boolean isEmpty() {
        return trees.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return trees.contains(o);
    }

    @Override
    public Iterator<Tree> iterator() {
        return trees.iterator();
    }

    @Override
    public Object[] toArray() {
        return trees.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return trees.toArray(ts);
    }

    @Override
    public boolean add(Tree tree) {
        return trees.add(tree);
    }

    @Override
    public boolean remove(Object o) {
        return trees.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return trees.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Tree> collection) {
        return trees.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Tree> collection) {
        return trees.addAll(i, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return trees.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return trees.retainAll(collection);
    }

    @Override
    public void replaceAll(UnaryOperator<Tree> unaryOperator) {
        trees.replaceAll(unaryOperator);
    }

    @Override
    public void sort(Comparator<? super Tree> comparator) {
        trees.sort(comparator);
    }

    @Override
    public void clear() {
        trees.clear();
    }

    @Override
    public boolean equals(Object o) {
        return trees.equals(o);
    }

    @Override
    public int hashCode() {
        return trees.hashCode();
    }

    @Override
    public Tree get(int i) {
        return trees.get(i);
    }

    @Override
    public Tree set(int i, Tree tree) {
        return trees.set(i, tree);
    }

    @Override
    public void add(int i, Tree tree) {
        trees.add(i, tree);
    }

    @Override
    public Tree remove(int i) {
        return trees.remove(i);
    }

    @Override
    public int indexOf(Object o) {
        return trees.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return trees.lastIndexOf(o);
    }

    @Override
    public ListIterator<Tree> listIterator() {
        return trees.listIterator();
    }

    @Override
    public ListIterator<Tree> listIterator(int i) {
        return trees.listIterator(i);
    }

    @Override
    public List<Tree> subList(int i, int i1) {
        return trees.subList(i, i1);
    }

    @Override
    public Spliterator<Tree> spliterator() {
        return trees.spliterator();
    }

    @Override
    public String toString() {
        return this.trees.stream().map(Tree::toString).reduce((l, r) -> l + " " + r).orElse("()");
    }
}
