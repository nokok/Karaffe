/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm;

import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class AvailableTypesTest {

    private AvailableTypes availableTypes;

    @Before
    public void setUp() {
        availableTypes = new AvailableTypes();
    }

    @Test
    public void testResolveClass() {
        Optional<String> className = availableTypes.resolve("String");
        assertThat(className.isPresent(), is(true));
        assertThat(className.get(), is("java/lang/String"));
    }

    @Test
    public void testAddImport() {
        availableTypes.addImport("Character", "java/lang/Character");
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidImport() {
        availableTypes.addImport("Hoge", "invalid/Package/Class");
    }

    @Test
    public void testClear() {
    }

}
