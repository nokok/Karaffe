package org.karaffe.compiler.parser;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class TypeParserTest {

    @Test
    public void testTypeParser() {
        final Map<String, Boolean> sourceMap = new HashMap<>();
        sourceMap.put("int[]", true);
        sourceMap.put("int []", true);
        sourceMap.put("boolean", true);
        sourceMap.put("int", true);
        sourceMap.put("String", true);

        for (final Map.Entry<String, Boolean> entry : sourceMap.entrySet()) {
            final KaraffeLexer lexer = new KaraffeLexer(entry.getKey());
            final TypeParser parser = new TypeParser();
            final MatchResult result = parser.parse(lexer.run());
            Assert.assertEquals(entry.getValue(), result.isSuccess());
            if (entry.getValue()) {
                if (result.next().isEmpty()) {
                    return;
                }
                final MatchResult eofResult = new EOFParser().match(result.next());
                if (eofResult.isFailure()) {
                    Assert.fail(eofResult.toString());
                }
                Assert.assertEquals(0, eofResult.next().size());
            }
        }
    }
}
