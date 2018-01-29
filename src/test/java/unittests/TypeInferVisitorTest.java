package unittests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.lexer.KaraffeLexer;
import org.karaffe.compiler.lexer.Token;
import org.karaffe.compiler.parser.ClassDefParser;
import org.karaffe.compiler.parser.KaraffeParser;
import org.karaffe.compiler.parser.VarDefParser;
import org.karaffe.compiler.phases.inferer.TypeInferVisitor;
import org.karaffe.compiler.tree.base.Node;

public class TypeInferVisitorTest {

	@Ignore
    @Test
    public void test1() {
        String code = "class A {"
                + "var a :Int = 1"
                + "}";
        List<Token> tokens = new KaraffeLexer(code).run();
        Node node = new ClassDefParser().parse(tokens).getNode().get();
        Node normalized = node.normalize(new NormalizeContext());
        TypeInferVisitor visitor = new TypeInferVisitor();
        normalized.accept(visitor);
        assertEquals("karaffe.core.Int", visitor.getType("a").get());
    }

	@Ignore
    @Test
    public void test2() {
    	String code = "let a: Int = 1";
    	List<Token> tokens = new KaraffeLexer(code).run();
    	Node node = new VarDefParser().parse(tokens).getNode().get();
    	Node normalized = node.normalize(new NormalizeContext());
        TypeInferVisitor visitor = new TypeInferVisitor();
    	normalized.accept(visitor);
    	assertEquals("karaffe.core.Int", visitor.getType("a").get());
    }

    @Test
    public void test() {
        String code = "class Factorial {\r\n" +
                "    public static void main(String[] a) {\r\n" +
                "        System.out.println((new Fac()).computeFac(10));\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "\r\n" +
                "class Fac {\r\n" +
                "    public int computeFac(int num){\r\n" +
                "        var numAux :int ;\r\n" +
                "        if (num < 1)\r\n" +
                "            numAux = 1;\r\n" +
                "        else\r\n" +
                "            numAux = num * (this.ComputeFac(num-1)) ;\r\n" +
                "        return numAux;\r\n" +
                "    }\r\n" +
                "}\r\n" +
                "";
        KaraffeParser parser = new KaraffeParser();
        Node node = parser.parse(new KaraffeLexer(code).run()).getNode().get();
        Node normalized = node.normalize(new NormalizeContext());
        TypeInferVisitor infVisitor = new TypeInferVisitor();
        normalized.accept(infVisitor);
    }
}
