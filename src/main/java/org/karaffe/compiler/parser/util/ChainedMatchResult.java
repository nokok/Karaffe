package org.karaffe.compiler.parser.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.tree.base.Node;

public class ChainedMatchResult implements MatchResult {
    final List<Token> matched = new ArrayList<>();
    private final Map<TokenMatcher, MatchResult> map = new LinkedHashMap<>();
    private Tokens last;

    public int size() {
        return this.map.size();
    }

    public MatchResult addResult(final TokenMatcher key, final MatchResult value) {
        if (value.isSuccess()) {
            this.matched.addAll(value.matchedF());
            this.last = value.next();
        }
        return this.map.put(key, value);
    }

    public Set<Entry<TokenMatcher, MatchResult>> entrySet() {
        return this.map.entrySet();
    }

    public Set<Entry<TokenMatcher, Optional<Node>>> nodeSet() {
        final Map<TokenMatcher, Optional<Node>> ret = new LinkedHashMap<>();
        for (final Map.Entry<TokenMatcher, MatchResult> p : this.map.entrySet()) {
            ret.put(p.getKey(), p.getValue().getNode());
        }
        return ret.entrySet();
    }

    @Override
    public boolean isSuccess() {
        return this.map.size() == this.map.values().stream().filter(MatchResult::isSuccess).count();
    }

    @Override
    public Optional<List<Token>> matched() {
        return Optional.of(this.matched);
    }

    @Override
    public Tokens next() {
        return this.last;
    }

}
