package net.nokok.karaffe.javacc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TypePoolTest {

    private StructPool pool;

    @Before
    public void setUp() {
        pool = new StructPool();
    }

    @Test
    public void testAddType() {
        pool.addType("Any");
        assertThat(pool.typeCount(), is(1));
    }

    @Test(expected = RuntimeException.class)
    public void testDuplicateTypeName() {
        pool.addType("Any");
        pool.addType("Any");
    }
}
