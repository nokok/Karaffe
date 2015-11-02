package karaffe.compiler;

import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.objectweb.asm.Type;

/**
 *
 * @author noko
 */
public class ClassResolverTest {

    @Test
    public void testResolveInternalNameByIdent() {
        ClassResolver resolver = new ClassResolver();
        Optional<String> mayBeClassName = resolver.resolveInternalNameByIdent("Appendable");
        assertThat(mayBeClassName.isPresent(), is(true));
        assertThat(mayBeClassName.get(), is(Type.getInternalName(Appendable.class)));
    }

    @Test
    public void testResolveTypeByIdent() {
        ClassResolver resolver = new ClassResolver();
        Optional<Type> mayBeType = resolver.resolveTypeByIdent("Appendable");
        assertThat(mayBeType.isPresent(), is(true));
        assertThat(mayBeType.get(), is(Type.getType(Appendable.class)));
    }

    @Test
    public void testResolveClassByIdent() {
        ClassResolver resolver = new ClassResolver();
        Optional<Class<?>> clazz = resolver.resolveClassByIdent("Appendable");
        assertThat(clazz.isPresent(), is(true));
        assertThat(clazz.get().getName(), is(Appendable.class.getName()));
    }
}
