package unittests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.resolvers.MethodResolver;

public class MethodResolverTest {
    @Test
    public void testMethodResolver1() {
        Optional<List<Method>> methodsOpt = MethodResolver.findMethods("java.lang.Object.toString");
        assertEquals(1, methodsOpt.get().size());
    }
}
