package org.karaffe.compiler.lexer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.karaffe.compiler.lexer.CommonToken.EOF;

public class Tokens implements Collection<Token> {
    private final Collection<Token> tokens;

    public Tokens() {
        this(new ArrayList<>(0));
    }

    public Tokens(final Collection<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public boolean add(final Token e) {
        return this.tokens.add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends Token> c) {
        return this.tokens.addAll(c);
    }

    @Override
    public void clear() {
        this.tokens.clear();
    }

    @Override
    public boolean contains(final Object o) {
        return this.tokens.contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return this.tokens.containsAll(c);
    }

    @Override
    public boolean equals(final Object o) {
        return this.tokens.equals(o);
    }

    public List<Token> getErrorTokens() {
        return this.tokens
                .stream()
                .filter(Token::isErrorToken)
                .collect(Collectors.toList());
    }

    public boolean hasErrorToken() {
        return this.tokens
                .stream()
                .filter(Token::isErrorToken)
                .count() > 0;
    }

    @Override
    public int hashCode() {
        return this.tokens.hashCode();
    }

    @Override
    public boolean isEmpty() {
        if (this.tokens.isEmpty()) {
            return true;
        }
        if ((this.tokens.size() == 1) && this.tokens.iterator().next().is(EOF.class)) {
            return true;
        }
        for (final Token token : this.tokens) {
            if (!(token.isWhiteSpace() || token.is(EOF.class))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<Token> iterator() {
        return this.tokens.iterator();
    }

    @Override
    public boolean remove(final Object o) {
        return this.tokens.remove(o);
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
    public int size() {
        return this.tokens.size();
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
    public String toString() {
        return this.tokens.stream().filter(t -> !t.isWhiteSpace()).collect(Collectors.toList()).toString();
    }

}
