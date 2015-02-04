/**
 * Karaffe Programming Language
 */
package net.nokok.karaffe.parser.asm.typechecker;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InfererTest {

    @Test
    public void testInferer() {
        Inferer inferer = new Inferer(1 + 1);
        assertThat(inferer.inferredClass().getName(), is(Integer.class.getName()));
    }
}
