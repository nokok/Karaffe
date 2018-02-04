package unittests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.tree.Select;
import org.karaffe.compiler.types.TypeResolver;

import karaffe.core.Int;

public class TypeResolverTest {

    @Test
    public void testInvalid() {
        assertFalse(TypeResolver.buildSuperClasses("Object").isPresent());
    }

    @Test
    public void testObject() {
        Optional<List<String>> superClassesOpt = TypeResolver.buildSuperClasses("java.lang.Object");
        List<String> superClasses = superClassesOpt.get();
        assertEquals(0, superClasses.size());
    }

    @Test
    public void testNumber() {
        Optional<List<String>> superClassesOpt = TypeResolver.buildSuperClasses("java.lang.Number");
        List<String> superClasses = superClassesOpt.get();
        assertEquals(1, superClasses.size());
        assertEquals("java.lang.Object", superClasses.get(0));
    }

    @Test
    public void testSomeClasses() {
        assertArrayEquals(
                Arrays.asList().toArray(),
                TypeResolver.buildSuperClasses("java.lang.Object").get().toArray());
        assertArrayEquals(
                Arrays.asList("java.lang.Object").toArray(),
                TypeResolver.buildSuperClasses("java.lang.Number").get().toArray());
        assertArrayEquals(
                Arrays.asList("java.lang.Number",
                        "java.lang.Object").toArray(),
                TypeResolver.buildSuperClasses("java.lang.Integer").get().toArray());
        assertArrayEquals(
                Arrays.asList("karaffe.core.Any",
                        "java.lang.Object").toArray(),
                TypeResolver.buildSuperClasses("karaffe.core.Int").get().toArray());
        assertArrayEquals(
                Arrays.asList("java.io.InputStreamReader",
                        "java.io.Reader",
                        "java.lang.Object").toArray(),
                TypeResolver.buildSuperClasses("java.io.FileReader").get().toArray());
    }

    @Test
    public void testFindAllCompatibleClasses1() {
        assertArrayEquals(
                Arrays.asList("java.lang.Object").toArray(),
                TypeResolver.findAllCompatibleClasses("java.lang.Object").get().toArray());
        assertArrayEquals(
                Arrays.asList(
                        "java.lang.Number",
                        "java.io.Serializable",
                        "java.lang.Object").toArray(),
                TypeResolver.findAllCompatibleClasses("java.lang.Number").get().toArray());
        assertArrayEquals(
                Arrays.asList(
                        "java.io.FileReader",
                        "java.io.InputStreamReader",
                        "java.io.Reader",
                        "java.lang.Readable",
                        "java.io.Closeable",
                        "java.lang.AutoCloseable",
                        "java.lang.Object").toArray(),
                TypeResolver.findAllCompatibleClasses("java.io.FileReader").get().toArray());
    }

    @Test
    public void testFindMethodArgsType() {
        class A {
            public void none() {

            }

            public void i(Int i) {

            }

            public void ii(Int i1, Int i2) {

            }
        }

        assertFalse(TypeResolver.findMethodArgsType(A.class, "invalidname").isPresent());
        assertTrue(TypeResolver.findMethodArgsType(A.class, "none").get().isEmpty());
        assertEquals(new Select("karaffe", "core", "Int").toString(), TypeResolver.findMethodArgsType(A.class, "i").get().get(0).toString());
        assertEquals(new Select("karaffe", "core", "Int").toString(), TypeResolver.findMethodArgsType(A.class, "ii").get().get(0).toString());
        assertEquals(new Select("karaffe", "core", "Int").toString(), TypeResolver.findMethodArgsType(A.class, "ii").get().get(1).toString());
    }
}
