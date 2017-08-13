package org.karaffe.compiler.parser.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.OperatorToken.LeftBrace;
import org.karaffe.compiler.lexer.OperatorToken.RightBrace;

public class OptionalTokenMatcherTest {

    @Test
    public void testOptional() {
        final TokenMatcher matcher = new OptionalTokenMatcher(TokenMatcher.create(LeftBrace.class, RightBrace.class));

        final Map<String, Boolean> sourceMap = new HashMap<>();
        sourceMap.put("{}", true);
        sourceMap.put("", true);

        for (final Map.Entry<String, Boolean> entry : sourceMap.entrySet()) {
            final KaraffeLexer lexer = new KaraffeLexer(entry.getKey());
            final TokenMatcher optional = new OptionalTokenMatcher(matcher);
            final MatchResult result = optional.match(lexer.run());
            Assert.assertEquals(entry.getKey() + " " + result, entry.getValue(), result.isSuccess());
            Assert.assertEquals(entry.getKey() + " " + result, 1 /* EOF */, result.next().size());
        }
    }

}