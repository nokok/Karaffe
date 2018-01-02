package org.karaffe.compiler.phases;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.tree.CompileUnit;

public class TypeCheckerPhaseTest {
    @Test
    public void testTypeEnvBuilder() {
        KaraffeParser parser = new KaraffeParser();
        MatchResult parse = parser.parse(new KaraffeLexer("class Factorial {\n" +
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
                "}\n").run());
        CompileUnit node = parse.getNode().map(CompileUnit.class::cast).get();
        TypeCheckerPhase phase = new TypeCheckerPhase();
        Optional<CompileUnit> result = phase.transform(node);
    }
}
