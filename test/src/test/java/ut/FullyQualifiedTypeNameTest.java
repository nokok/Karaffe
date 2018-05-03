package ut;

import org.junit.Test;
import org.karaffe.compiler.base.pos.Position;
import org.karaffe.compiler.frontend.karaffe.ast.names.FullyQualifiedTypeName;
import org.karaffe.compiler.frontend.karaffe.ast.names.SimpleName;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FullyQualifiedTypeNameTest {

    @Test
    public void testCtorFromClassLiteral() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(Object.class).toString());
    }

    @Test
    public void testCtorFromStringsWithPosition() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(Position.noPos(), "java", "lang", "Object").toString());
    }

    @Test
    public void testCtorFromSimpleNamesWithPosition() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(Position.noPos(), new SimpleName("java"), new SimpleName("lang"), new SimpleName("Object")).toString());
    }

    @Test
    public void testCtorFromListWithPosition() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(Position.noPos(), Arrays.asList(new SimpleName("java"), new SimpleName("lang"), new SimpleName("Object"))).toString());
    }

    @Test
    public void testCtorFromStringsVarargs() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName("java", "lang", "Object").toString());
    }

    @Test
    public void testCtorSimpleNames() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(new SimpleName("java"), new SimpleName("lang"), new SimpleName("Object")).toString());
    }

    @Test
    public void testCtorFromList() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(Arrays.asList(new SimpleName("java"), new SimpleName("lang"), new SimpleName("Object"))).toString());
    }

    @Test
    public void testCtorOtherObject() {
        assertEquals("java.lang.Object", new FullyQualifiedTypeName(new FullyQualifiedTypeName(Object.class)).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIntLiteral() {
        assertNotEquals("int", new FullyQualifiedTypeName(int.class).toString());
    }

    @Test
    public void test() {
        FullyQualifiedTypeName fqn = new FullyQualifiedTypeName(Object.class);
        assertEquals("java.lang.Object", fqn.getFullName());
        assertEquals("Object", fqn.getName().toString());
        assertEquals(0, fqn.getParameterizedTypes().size());
    }

    @Test
    public void testSimpleName1() {
        new FullyQualifiedTypeName(new SimpleName("Invalid"));
    }

}
