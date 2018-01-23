package org.karaffe.compiler.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.parser.util.ResultState;

public class KaraffeParserTest {

    @Test
    public void testParser() {
        final KaraffeLexer lexer = new KaraffeLexer("package path.to.pkg;");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        Assert.assertEquals(9, tokens.size());

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
        final KaraffeLexer lexer = new KaraffeLexer("class A {var a :int;} class B {var a :int;}");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        Assert.assertEquals(28, tokens.size());

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
                "        var numAux :int ;\n" +
                "        if (num < 1)\n" +
                "            numAux = 1;\n" +
                "        else\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                "        return numAux;\n" +
                "    }\n" +
                "}\n");
        final KaraffeParser parser = new KaraffeParser();
        final List<Token> tokens = lexer.run();

        final MatchResult result = parser.parse(tokens);
        Assert.assertTrue(result.isSuccess());
    }

    @Test
    public void testMiniJavaKnormal() {
        KaraffeLexer lexer = new KaraffeLexer("class Factorial {\n" +
                "    public static void main(String[] a) {\n" +
                "        ClassRef c1 = system;\n" +
                "        PrintStream t1 = c1.out;\n" +
                "        Fac e1 = new Fac();\n" +
                "        int e2 = 10;\n" +
                "        int e3 = e1.computeFac(e2);\n" +
                "        void r1 = t1.println(e3);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Fac {\n" +
                "    public int computeFac(int num){\n" +
                "        int numAux = 0;\n" +
                "        boolean e1 = num < 1;\n" +
                "        if ( e1 ) {\n" +
                "          numAux = 1;\n" +
                "          int a = 0;\n" +
                "        } else {\n" +
                "          int e1 = num - 1;\n" +
                "          int b = a;\n" +
                "          int e2 = this.computeFac(e1);\n" +
                "          int e3 = num * e2;\n" +
                "          numAux = e3;\n" +
                "        }\n" +
                "        return numAux;\n" +
                "    }\n" +
                "}\n");
        List<Token> tokens = lexer.run();
        KaraffeParser parser = new KaraffeParser();
        MatchResult result = parser.parse(tokens);
        System.out.println(result);

    }

}
