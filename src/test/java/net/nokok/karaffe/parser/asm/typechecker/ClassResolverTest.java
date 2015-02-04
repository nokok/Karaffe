/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

import java.util.Optional;
import javax.swing.JButton;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.Type;

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
    public void testResolveType() {
        Optional<Type> type = resolver.resolveType("String");
        assertThat(type.isPresent(), is(true));
        assertThat(type.get(), is(Type.getType(String.class)));
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
        String name = "JButton";
        String dummyPath = JButton.class.getCanonicalName();
        resolver.addImport(name, dummyPath);
        Optional<Class<?>> typePath = resolver.resolve(name);
        assertThat(typePath.isPresent(), is(true));
        assertThat(typePath.get().getName(), is(dummyPath));
        resolver.clear();
        assertThat(resolver.resolve(name), is(Optional.empty()));
    }
}
