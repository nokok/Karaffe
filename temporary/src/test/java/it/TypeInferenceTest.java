package it;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeContext;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.parser.StatementParser;
import org.karaffe.compiler.tree.Name;
import org.karaffe.compiler.tree.NodeList;
import org.karaffe.compiler.tree.base.Node;
import org.karaffe.compiler.types.InferResult;

@Ignore
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
        assertEquals("karaffe.core.String", infer("new String()", new ExprParser()));
    }

    @Test
    public void test7() {
        assertEquals("karaffe.core.IntArray", infer("new int[0]", new ExprParser()));
    }

    @Test
    public void test8() {
        assertEquals("karaffe.core.IntArray", infer("new int[0 + 1]", new ExprParser()));
    }

    @Test
    public void test9() {
        assertEquals("karaffe.core.Int", infer("{"
                + "let a = 0\n"
                + "let b = a + 2"
                + "}", new StatementParser(), "b"));
    }

    private String infer(String code, Parser parser) {
        return infer(code, parser, null);
    }

    private String infer(String code, Parser parser, String name) {
        Node nodes = parser.parse(code).getNode().get();
        final NodeList node = nodes.normalize(new NormalizeContext());
        final TypeContext context = TypeContext.create();
        Optional<InferResult> resultOpt = node.tryTypeInference(context);
        InferResult inferResult = resultOpt.get();
        if (name == null) {
            return inferResult.getType().get();
        }
        return context.getInferredType(new Name(name)).get().getType().get();
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
