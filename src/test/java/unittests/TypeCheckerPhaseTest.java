package unittests;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.util.MatchResult;
import org.karaffe.compiler.phases.TypeCheckerPhase;
import org.karaffe.compiler.tree.CompileUnit;

public class TypeCheckerPhaseTest {
    @Test
    public void testTypeEnvBuilder() {
        final KaraffeParser parser = new KaraffeParser();
        final MatchResult parse = parser.parse(new KaraffeLexer("class Factorial {\n" +
                "    public static void main(String[] a) {\n" +
                "        System.out.println((new Fac()).computeFac(10));\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "class Fac {\n" +
                "    public int computeFac(int num){\n" +
                "        var numAux :int;\n" +
                "        if (num < 1)\n" +
                "            numAux = 1;\n" +
                "        else\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\n" +
                "        return numAux;\n" +
                "    }\n" +
                "}\n").run());
        final CompileUnit node = parse.getNode().map(CompileUnit.class::cast).get();
        final TypeCheckerPhase phase = new TypeCheckerPhase();
        final Optional<CompileUnit> result = phase.transform(node);
    }
}
