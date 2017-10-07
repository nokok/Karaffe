package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.ResultState;

public class KaraffeParserTest {

    @Test
    public void testParser() {
        final KaraffeLexer lexer = new KaraffeLexer("package path.to.pkg");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        Assert.assertEquals(8, tokens.size());

        final ResultState result = parser.parse(tokens);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testClassDecl() {
        final KaraffeLexer lexer = new KaraffeLexer("class { class A {}");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        Assert.assertEquals(12, tokens.size());

        final ResultState result = parser.parse(tokens);
        Assert.assertTrue("should be failure but success.", result.isFailure());
    }

    @Test
    public void testClassDecl2() {
        final KaraffeLexer lexer = new KaraffeLexer("class A {int a;} class B {int a;}");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        Assert.assertEquals(22, tokens.size());

        final ResultState result = parser.parse(tokens);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testMiniJava() {
        final KaraffeLexer lexer = new KaraffeLexer("class Factorial {\n" +
                "    public static void main(String[] a) {\n" +
                "        System.out.println((new Fac()).computeFac(10));\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Fac {\n" +
                "    public int computeFac(int num){\n" +
                "        int numAux ;\n" +
                "        if (num < 1)\n" +
                "            numAux = 1;\n" +
                "        else\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                "        return numAux;\n" +
                "    }\n" +
                "}\n");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        final ResultState result = parser.parse(tokens);
        Assert.assertTrue(result.isSuccess());
    }

}
