package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;

public class LineColPosTest {
    private final Position pos = Position.of("A", 1, 3);

    @Test
    public void testLine() {
        assertEquals("1", pos.getLine());
    }

    @Test
    public void testLineNumber() {
        assertEquals(1, pos.getLineNumber().get().intValue());
    }

    @Test
    public void testCol() {
        assertEquals("3", pos.getCol());
    }

    @Test
    public void testColNumber() {
        assertEquals(3, pos.getColNumber().get().intValue());
    }

    @Test
    public void testSourceName() {
        assertEquals("A", pos.getSourceName());
    }

    @Test
    public void testFlags() {
        assertFalse(pos.isNoPos());
        assertFalse(pos.isRange());
        assertFalse(pos.asRange().isPresent());
    }

    @Test
    public void testCopy() {
        Position otherPos = Position.copy(pos);
        assertTrue(pos.equals(otherPos));
    }

}
