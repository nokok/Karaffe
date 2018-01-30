package unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;

public class LineColPosTest {
    private final Position pos = Position.of("A", 1, 3);

    @Test
    public void testCol() {
        assertEquals("3", this.pos.getCol());
    }

    @Test
    public void testColNumber() {
        assertEquals(3, this.pos.getColNumber().get().intValue());
    }

    @Test
    public void testCopy() {
        final Position otherPos = Position.copy(this.pos);
        assertTrue(this.pos.equals(otherPos));
    }

    @Test
    public void testFlags() {
        assertFalse(this.pos.isNoPos());
        assertFalse(this.pos.isRange());
        assertFalse(this.pos.asRange().isPresent());
    }

    @Test
    public void testLine() {
        assertEquals("1", this.pos.getLine());
    }

    @Test
    public void testLineNumber() {
        assertEquals(1, this.pos.getLineNumber().get().intValue());
    }

    @Test
    public void testSourceName() {
        assertEquals("A", this.pos.getSourceName());
    }

}
