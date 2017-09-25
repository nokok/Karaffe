package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;

public class PackageDeclTest {

    @Test
    public void testPackage1() {
        this.runTest("package path.to.pkg", true);
    }

    @Test
    public void testPackage2() {
        this.runTest("package a", true);
    }

    @Test
    public void testPackage3() {
        this.runTest("package a.b", true);
    }

    @Test
    public void testPackage4() {
        this.runTest("package a.b.c", true);
    }

    @Test
    public void testPackage5() {
        this.runTest("package a.b.c.d.e", true);
    }

    @Test
    public void testPackage6() {
        this.runTest("package pkg", true);
    }

    @Test
    public void testPackage7() {
        this.runTest("package pkg.to", true);
    }

    @Test
    public void testPackage8() {
        this.runTest("package pkg.to.path", true);
    }

    @Test
    public void testPackage9() {
        this.runTest("package a", true);
    }

    @Test
    public void testPackage10() {
        this.runTest("package a . b. \t b ", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new PackageDefParser();
        final List<Token> input = lexer.run();
        final MatchResult result = parser.parse(input);
        Assert.assertEquals(source + " " + result, v, result.isSuccess());
        if (v) {
            if (result.next().isEmpty()) {
                return;
            }
            final MatchResult eofResult = new EOFParser().match(result.next());
            if (eofResult.isFailure()) {
                Assert.fail(eofResult.toString());
            }
            Assert.assertEquals(0, eofResult.next().size());
        } else {
            Assert.assertEquals(input.size(), result.next().size());
        }
    }

}
