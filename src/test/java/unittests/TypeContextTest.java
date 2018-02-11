package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.StatementParser;
import org.karaffe.compiler.tree.NodeList;

public class TypeContextTest {
    @Test
    public void testDefaultImportPackages() {
        TypeContext context = TypeContext.create();
        assertEquals("java.lang.Integer", context.findFQCN("Integer").get());
        assertEquals("java.time.LocalDateTime", context.findFQCN("LocalDateTime").get());
        assertEquals("java.time.LocalDate", context.findFQCN("LocalDate").get());
    }

    @Test
    public void testDefaultImportClasses() {
        TypeContext context = TypeContext.create();
        assertEquals("java.math.BigInteger", context.findFQCN("BigInteger").get());
        assertEquals("java.math.BigDecimal", context.findFQCN("BigDecimal").get());
    }

    @Test
    public void testFindFQCN() {
        TypeContext context = TypeContext.create();
        assertEquals("java.lang.Object", context.findFQCN("java.lang.Object").get());
    }

    @Test
    public void testDefName1() {
        TypeContext context = TypeContext.create();
        NodeList nodeList = new ExprParser().parse("1+2").getNode().get().normalize(new NormalizeContext());
        nodeList.tryTypeInference(context);
        assertEquals("karaffe.core.Int | karaffe.core.Any | java.lang.Object", context.getInferredType("kn_0").get().toString());
        assertEquals("?#+", context.getInferredType("kn_1").get().toString());
        assertEquals("karaffe.core.Int#+", context.getInferredType("kn_2").get().toString());
        assertEquals("karaffe.core.Int | karaffe.core.Any | java.lang.Object", context.getInferredType("kn_3").get().toString());
        assertEquals("karaffe.core.Int", context.getInferredType("kn_4").get().toString());
    }

    @Test
    public void testDefName2() {
        TypeContext context = TypeContext.create();
        NodeList nodeList = new StatementParser().parse("{"
                + "let a = 0;\n"
                + "let b = a + 2"
                + "}").getNode().get().normalize(new NormalizeContext());
        nodeList.tryTypeInference(context);
        assertEquals("karaffe.core.Int | karaffe.core.Any | java.lang.Object", context.getInferredType("a").get().toString());
        assertEquals("karaffe.core.Int", context.getInferredType("b").get().toString());
    }

}
