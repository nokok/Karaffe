package net.nokok.karaffe.javacc;

import net.nokok.karaffe.javacc.identifier.TypeId;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class TypeIdTest {

    @Test(expected = RuntimeException.class)
    public void testStartWithLowerCaseTypeName() {
        TypeId t = new TypeId(new Name("hoge"));
    }

    @Test
    public void testIsTypeParameter() {
        TypeId t = new TypeId(new Name("a"));
        assertThat(t.isTypeParameter(), is(true));
        assertThat(t.isInterface(), is(false));
    }

    @Test
    public void testIsNotTypeParameter() {
        TypeId t = new TypeId(new Name("HogeType"));
        assertThat(t.isTypeParameter(), is(false));
        assertThat(t.isInterface(), is(false));
    }

    @Test
    public void testIsInterface() {
        TypeId t = new TypeId(new Name("IHoge"));
        assertThat(t.isInterface(), is(true));
    }

}
