package org.karaffe.compiler.pos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.karaffe.compiler.pos.NoPos;
import org.karaffe.compiler.pos.Pos;

public class PositionTest {
    @Test
    public void testNoPos() {
        Pos position = Pos.noPos();
        position.asNoPos().get();
        NoPos noPos = position.asNoPos().get();
        assertEquals("<no-pos>", position.toString());
        assertEquals("<no-pos>", noPos.toString());
        assertFalse(position.asLineAndColumn().isPresent());
    }

    @Test
    public void testLineAndColumn() {
        Pos position = Pos.fromLineAndColumn(1, 2);
        LineColumnPosition lcPos = position.asLineAndColumn().get();
        assertEquals("1:2", position.toString());
        assertEquals("1:2", lcPos.toString());
        assertFalse(position.asNoPos().isPresent());
    }
}
