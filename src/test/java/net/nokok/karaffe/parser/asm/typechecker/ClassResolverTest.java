/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ClassResolverTest {

    private ClassResolver resolver;

    @Before
    public void setUp() {
        resolver = new ClassResolver();
    }

    @Test
    public void testResolveClass() throws ClassNotFoundException {
        Optional<Class<?>> className = resolver.resolve("String");
        assertThat(className.isPresent(), is(true));
        assertThat(className.get().getName(), is("java.lang.String"));
    }

    @Test
    public void testAddImport() throws ClassNotFoundException {
        resolver.addImport("Character", "java.lang.Character");
    }

    @Test(expected = ClassNotFoundException.class)
    public void testInvalidImport() throws ClassNotFoundException {
        resolver.addImport("Hoge", "invalid.Package.Class");
    }

    @Test
    public void testClear() throws ClassNotFoundException {
        resolver.addImport("Cloneable", "java.lang.Cloneable");
        Optional<Class<?>> typePath = resolver.resolve("Cloneable");
        assertThat(typePath.isPresent(), is(true));
        assertThat(typePath.get().getName(), is("java.lang.Cloneable"));
        resolver.clear();
        assertThat(resolver.resolve("Cloneable"), is(Optional.empty()));
    }

}
