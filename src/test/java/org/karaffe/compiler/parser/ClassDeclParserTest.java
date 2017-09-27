package org.karaffe.compiler.parser;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Tokens;
import org.karaffe.compiler.parser.util.MatchResult;

public class ClassDeclParserTest {

    @Test
    public void testClassDecl1() {
        this.runTest("class A", true);
    }

    @Test
    public void testClassDecl2() {
        this.runTest("class A{}", true);
    }

    @Test
    public void testClassDecl3() {
        this.runTest("class A {int a;}", true);
    }

    @Test
    public void testClassDecl4() {
        this.runTest("class A{int a;int b; String c;}", true);
    }

    @Test
    public void testClassDecl5() {
        this.runTest("class A{\n"
                + "     int a;\n"
                + "     public int doSomething(int a) {\n"
                + "       return a;\n"
                + "     }\n"
                + "   }", true);
    }

    @Test
    public void testClassDecl6() {
        this.runTest("class A extends Base{ }", true);
    }

    @Test
    public void testClassDecl7() {
        this.runTest("class A extends Base {\n"
                + "     int a;\n"
                + "     int b;\n"
                + "     public int doSomething(int a) {\n"
                + "       return a;\n"
                + "     }\n"
                + "   }", true);
    }

    @Test
    public void testClassDecl8() {
        this.runTest("class A{public int doSomething(int a) {return false;}}", true);
    }

    private void runTest(final String source, final boolean v) {
        final KaraffeLexer lexer = new KaraffeLexer(source);
        final Parser parser = new ClassDefParser();
        final MatchResult result = parser.parse(new Tokens(lexer.run()));
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
        }
    }

}
