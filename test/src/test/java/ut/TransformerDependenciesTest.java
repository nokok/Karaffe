package ut;

import org.junit.Test;
import org.karaffe.compiler.frontend.karaffe.transformer.AbstractTransformer;
import org.karaffe.compiler.frontend.karaffe.transformer.TransformerDependencies;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TransformerDependenciesTest {

    private TransformerDependencies transformerDependencies = new TransformerDependencies();

    @Test
    public void testDependencyBuilder1() {
        Set<AbstractTransformer> dependency = transformerDependencies.buildTransformers("create-context");
        assertEquals(1, dependency.size());
        assertEquals("create-context", dependency.iterator().next().getTransformerName());
    }

    @Test
    public void testDependencyBuilder2() {
        Set<AbstractTransformer> dependency = transformerDependencies.buildTransformers("type-checker");
        assertEquals(6, dependency.size());
        Iterator<AbstractTransformer> depIter = dependency.iterator();
        assertEquals("create-context", depIter.next().getTransformerName());
        assertEquals("default-imports", depIter.next().getTransformerName());
        assertEquals("name-resolver", depIter.next().getTransformerName());
        assertEquals("create-ctor", depIter.next().getTransformerName());
        assertEquals("type-inferer", depIter.next().getTransformerName());
        assertEquals("type-checker", depIter.next().getTransformerName());
    }

    @Test
    public void testDependencyBuilder3() {
        Set<AbstractTransformer> dependency = transformerDependencies.buildTransformers("opname-remapper");
        assertEquals(7, dependency.size());
        Iterator<AbstractTransformer> depIter = dependency.iterator();
        assertEquals("create-context", depIter.next().getTransformerName());
        assertEquals("default-imports", depIter.next().getTransformerName());
        assertEquals("name-resolver", depIter.next().getTransformerName());
        assertEquals("create-ctor", depIter.next().getTransformerName());
        assertEquals("type-inferer", depIter.next().getTransformerName());
        assertEquals("type-checker", depIter.next().getTransformerName());
        assertEquals("opname-remapper", depIter.next().getTransformerName());
    }
}
