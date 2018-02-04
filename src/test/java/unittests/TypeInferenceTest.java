package unittests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.context.NormalizeContext;
import org.karaffe.compiler.context.TypeInferenceContext;
import org.karaffe.compiler.parser.ExprParser;
import org.karaffe.compiler.parser.Parser;
import org.karaffe.compiler.tree.Name;
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

        NodeList nodeList = new ExprParser().parse("1").getNode().get().normalize(new NormalizeContext());
        TypeInferenceContext context = TypeInferenceContext.create();
        nodeList.tryTypeInference(context);
        Name expectedLastName = new Name("kn_0");
        Name actualLastName = (Name) nodeList.lastAssignName();
        assertEquals(expectedLastName, actualLastName);
        InferResult result = context.getInferredType(actualLastName).get();
        assertEquals("karaffe.core.Int", result.getType().get());
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

        // kn_0 : karaffe.core.Int = 1 // Successful
        // kn_1 : ?#plus = + // AnyTarget
        // kn_2 : int#plus = kn_0.kn_1 // MayBeApplicable
        // kn_3 : karaffe.core.Int = 2 // Successful
        // kn_4 : int = kn_2(kn_3) // Successful int#plus(int) -> int

        NodeList nodeList = new ExprParser().parse("1+2").getNode().get().normalize(new NormalizeContext());
        TypeInferenceContext context = TypeInferenceContext.create();
        Optional<InferResult> inferResult = nodeList.tryTypeInference(context);
        Name expectedLastName = new Name("kn_4");
        Name actualLastName = (Name) nodeList.lastAssignName();
        assertEquals(expectedLastName, actualLastName);
        InferResult result = context.getInferredType(actualLastName).get();
        assertEquals("karaffe.core.Int", result.getType().get());
    }

    private String infer(String code, Parser parser) {
        final NodeList node = parser.parse(code).getNode().get().normalize(new NormalizeContext());
        System.out.println(node);
        System.out.println(node.lastAssignName());
        final TypeInferenceContext context = TypeInferenceContext.create();
        node.tryTypeInference(context);
        final InferResult inferResult = context.getInferredType((Name) node.lastAssignName()).get();
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
