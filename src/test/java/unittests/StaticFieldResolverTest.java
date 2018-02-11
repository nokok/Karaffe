package unittests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.resolvers.StaticFieldResolver;

public class StaticFieldResolverTest {

    @Test
    public void testStaticFieldResolverTest() {
        Optional<Field> fieldOpt = StaticFieldResolver.findStaticField("java.lang.System.out");
        assertEquals("out", fieldOpt.get().getName());
    }
}
