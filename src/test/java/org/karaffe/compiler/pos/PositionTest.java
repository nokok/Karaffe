package org.karaffe.compiler.pos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class PositionTest {
    @Test
    public void testNoPos() {
        Pos position = Pos.noPos();
        position.asOpt(NoPos.class).get();
        NoPos noPos = position.asOpt(NoPos.class).get();
        assertEquals("<no-pos>", position.toString());
        assertEquals("<no-pos>", noPos.toString());
        assertFalse(position.asOpt(LineColumnPosition.class).isPresent());
    }

    @Test
    public void testLineAndColumn() {
        Pos position = Pos.fromLineAndColumn(1, 2);
        LineColumnPosition lcPos = position.asOpt(LineColumnPosition.class).get();
        assertEquals("1:2", position.toString());
        assertEquals("1:2", lcPos.toString());
        assertFalse(position.asOpt(NoPos.class).isPresent());
    }
}
