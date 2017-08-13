package org.karaffe.compiler.lexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Tokens implements List<Token> {
    private final List<Token> tokens;

    public Tokens() {
        this(new ArrayList<>(0));
    }

    public Tokens(final List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public int size() {
        return this.tokens.size();
    }

    @Override
    public boolean isEmpty() {
        return this.tokens.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return this.tokens.contains(o);
    }

    @Override
    public Iterator<Token> iterator() {
        return this.tokens.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.tokens.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return this.tokens.toArray(a);
    }

    @Override
    public boolean add(final Token e) {
        return this.tokens.add(e);
    }

    @Override
    public boolean remove(final Object o) {
        return this.tokens.remove(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return this.tokens.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends Token> c) {
        return this.tokens.addAll(c);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends Token> c) {
        return this.tokens.addAll(index, c);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return this.tokens.removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return this.tokens.retainAll(c);
    }

    @Override
    public void clear() {
        this.tokens.clear();
    }

    @Override
    public boolean equals(final Object o) {
        return this.tokens.equals(o);
    }

    @Override
    public int hashCode() {
        return this.tokens.hashCode();
    }

    @Override
    public Token get(final int index) {
        return this.tokens.get(index);
    }

    @Override
    public Token set(final int index, final Token element) {
        return this.tokens.set(index, element);
    }

    @Override
    public void add(final int index, final Token element) {
        this.tokens.add(index, element);
    }

    @Override
    public Token remove(final int index) {
        return this.tokens.remove(index);
    }

    @Override
    public int indexOf(final Object o) {
        return this.tokens.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return this.tokens.lastIndexOf(o);
    }

    @Override
    public ListIterator<Token> listIterator() {
        return this.tokens.listIterator();
    }

    @Override
    public ListIterator<Token> listIterator(final int index) {
        return this.tokens.listIterator(index);
    }

    @Override
    public List<Token> subList(final int fromIndex, final int toIndex) {
        return this.tokens.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        return this.tokens.stream().filter(t -> !t.isWhiteSpace()).collect(Collectors.toList()).toString();
    }

}
