package org.karaffe.compiler.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class IdentifierParserTest {

    @Test
    public void testIdentifier() {
        final Map<String, Boolean> sourceMap = new LinkedHashMap<>();
        sourceMap.put("", false);
        sourceMap.put("A", true);
        sourceMap.put("Bar", true);
        sourceMap.put("camera", true);

        for (final Map.Entry<String, Boolean> entry : sourceMap.entrySet()) {
            final KaraffeLexer lexer = new KaraffeLexer(entry.getKey());
            final Parser parser = new IdentifierParser();
            final MatchResult result = parser.parse(lexer.run());
            Assert.assertEquals(entry.getKey() + " " + result, entry.getValue(), result.isSuccess());
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
