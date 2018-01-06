package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;

public class NoPosTest {
    private final Position pos = Position.noPos();

    @Test
    public void testLine() {
        assertEquals("?", pos.getLine());
    }

    @Test
    public void testLineNumber() {
        assertEquals(Optional.empty(), pos.getLineNumber());
    }

    @Test
    public void testCol() {
        assertEquals("?", pos.getCol());
    }

    @Test
    public void testColNumber() {
        assertEquals(Optional.empty(), pos.getColNumber());
    }

    @Test
    public void testSourceName() {
        assertEquals("?", pos.getSourceName());
    }

    @Test
    public void testFlags() {
        assertTrue(pos.isNoPos());
        assertFalse(pos.isRange());
        assertFalse(pos.asRange().isPresent());
    }

    @Test
    public void testCopy() {
        Position otherPos = Position.copy(pos);
        assertTrue(pos.equals(otherPos));
    }

}
