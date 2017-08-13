package org.karaffe.compiler.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.util.MatchResult;

public class PackageDeclTest {

    @Test
    public void packageDeclTest() {

        final Map<String, Boolean> sourceMap = new LinkedHashMap<>();
        sourceMap.put("package path.to.pkg", true);
        sourceMap.put("package a", true);
        sourceMap.put("package a.b", true);
        sourceMap.put("package a.b.c", true);
        sourceMap.put("package a.b.c.d.e", true);
        sourceMap.put("package pkg", true);
        sourceMap.put("package pkg.to", true);
        sourceMap.put("package pkg.to.path", true);
        sourceMap.put("package a", true);
        sourceMap.put("package a . b. \t b ", true);

        for (final Map.Entry<String, Boolean> entry : sourceMap.entrySet()) {
            final KaraffeLexer lexer = new KaraffeLexer(entry.getKey());
            final Parser parser = new PackageDeclParser();
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
