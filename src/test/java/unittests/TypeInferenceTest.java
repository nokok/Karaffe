package unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.types.InferResult;

public class TypeInferenceTest {
    @Test
    public void test1() {
        assertEquals("karaffe.core.Int", infer("1", new ExprParser()));
    }

    @Test
    public void test2() {
        // 1

        // ↓

        // kn_0 : ? = 1

        // ↓

        // kn_0 : Int = 1
        assertEquals("karaffe.core.Int", infer("1", new ExprParser()));
    }

    @Test
    public void test3() {
        // 1 + 2

        // ↓

        // kn_0 : ? = 1
        // kn_1 : ? = +
        // kn_2 : ? = kn_0.kn_1
        // kn_3 : ? = 2
        // kn_4 : ? = kn_2(kn_3)

        // ↓

        // kn_0 : karaffe.core.Int = 1
        // kn_1 : ? = +
        // kn_2 : ? = kn_0.kn_1
        // kn_3 : karaffe.core.Int = 2
        // kn_4 : ? = kn_2(kn_3)

        // ↓

        // kn_0 : karaffe.core.Int = 1
        // kn_1 : ? = +
        // kn_2 : ? = kn_0.kn_1
        // kn_3 : karaffe.core.Int = 2
        // kn_4 : ? = kn_2(kn_3)

        // kn_0=karaffe.core.Int | karaffe.core.Any | java.lang.Object
        // kn_1=?#+
        // kn_2=karaffe.core.Int#+
        // kn_3=karaffe.core.Int | karaffe.core.Any | java.lang.Object
        // kn_4=karaffe.core.Int

        assertEquals("karaffe.core.Int", infer("1+2", new ExprParser()));
    }

    @Test
    public void test4() {
        assertEquals("karaffe.core.Int", infer("1+2+3", new ExprParser()));
    }

    @Test
    public void test5() {
        assertEquals("karaffe.core.Double", infer("1/2", new ExprParser()));
    }

    @Test
    public void test6() {
        assertEquals("", infer("", null));
    }

    private String infer(String code, Parser parser) {
        final NodeList node = parser.parse(code).getNode().get().normalize(new NormalizeContext());
        final TypeContext context = TypeContext.create();
        InferResult inferResult = node.tryTypeInference(context).get();
        return inferResult.getType().get();
    }

    @Test
    public void testEmpty() {
        assertEquals("karaffe.core.Any", InferResult.ofEmpty().getType().get());
    }

    @Test
    public void testOf() {
        assertEquals("karaffe.core.Any", InferResult.defaultTypeName());
    }
}
