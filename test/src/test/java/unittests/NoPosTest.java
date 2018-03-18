package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.base.pos.Position;

public class NoPosTest {
    private final Position pos = Position.noPos();

    @Test
    public void testCol() {
        assertEquals("?", this.pos.getCol());
    }

    @Test
    public void testColNumber() {
        assertEquals(Optional.empty(), this.pos.getColNumber());
    }

    @Test
    public void testCopy() {
        final Position otherPos = Position.copy(this.pos);
        assertTrue(this.pos.equals(otherPos));
    }

    @Test
    public void testFlags() {
        assertTrue(this.pos.isNoPos());
        assertFalse(this.pos.isRange());
        assertFalse(this.pos.asRange().isPresent());
    }

    @Test
    public void testLine() {
        assertEquals("?", this.pos.getLine());
    }

    @Test
    public void testLineNumber() {
        assertEquals(Optional.empty(), this.pos.getLineNumber());
    }

    @Test
    public void testSourceName() {
        assertEquals("?", this.pos.getSourceName());
    }

}
