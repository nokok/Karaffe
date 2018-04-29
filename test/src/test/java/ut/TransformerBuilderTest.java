package ut;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerBuilder;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TransformerBuilderTest {

    private TransformerBuilder transformerBuilder = new TransformerBuilder();

    @Test
    public void testDependencyBuilder1() {
        Set<AbstractTransformer> dependency = transformerBuilder.buildTransformers("create-context");
        assertEquals(4, dependency.size());
        Iterator<AbstractTransformer> depIter = dependency.iterator();
        assertEquals("default-imports", depIter.next().getTransformerName());
        assertEquals("name-resolver", depIter.next().getTransformerName());
        assertEquals("clean-up", depIter.next().getTransformerName());
        assertEquals("create-context", depIter.next().getTransformerName());
    }

    @Test
    public void testDependencyBuilder2() {
        Set<AbstractTransformer> dependency = transformerBuilder.buildTransformers("type-checker");
        assertEquals(6, dependency.size());
        Iterator<AbstractTransformer> depIter = dependency.iterator();
        assertEquals("default-imports", depIter.next().getTransformerName());
        assertEquals("name-resolver", depIter.next().getTransformerName());
        assertEquals("clean-up", depIter.next().getTransformerName());
        assertEquals("create-context", depIter.next().getTransformerName());
        assertEquals("type-inferer", depIter.next().getTransformerName());
        assertEquals("type-checker", depIter.next().getTransformerName());
    }

    @Test
    public void testDependencyBuilder3() {
        Set<AbstractTransformer> dependency = transformerBuilder.buildTransformers("opname-remapper");
        assertEquals(7, dependency.size());
        Iterator<AbstractTransformer> depIter = dependency.iterator();
        assertEquals("default-imports", depIter.next().getTransformerName());
        assertEquals("name-resolver", depIter.next().getTransformerName());
        assertEquals("clean-up", depIter.next().getTransformerName());
        assertEquals("create-context", depIter.next().getTransformerName());
        assertEquals("type-inferer", depIter.next().getTransformerName());
        assertEquals("type-checker", depIter.next().getTransformerName());
        assertEquals("opname-remapper", depIter.next().getTransformerName());
    }
}
