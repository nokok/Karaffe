package org.karaffe.compiler.util;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    @Test
    public void testIs() {
        final Position noPosition = Position.noPos();
        Assert.assertTrue(noPosition.is(NoPos.class));
        Assert.assertFalse(noPosition.is(LinePosition.class));
        Assert.assertFalse(noPosition.is(LineColumnPosition.class));
    }

    @Test
    public void testMatchNoPos() {
        Position.noPos().match(
                noPos -> {
                    return "OK";
                },
                l -> {
                    Assert.fail();
                    return "";
                },
                lc -> {
                    Assert.fail();
                    return "";
                });
    }

    @Test
    public void testMatchLinePos() {
        Position.ofLine(1).match(
                noPos -> {
                    Assert.fail();
                    return "";
                },
                l -> {
                    Assert.assertEquals(1, l.getLine());
                    return "OK";
                },
                lc -> {
                    Assert.fail();
                    return "";
                });
    }

    @Test
    public void testMatchLineColPos() {
        Position.ofLineWithColumn(1, 2).match(
                noPos -> {
                    Assert.fail();
                    return "";
                },
                l -> {
                    Assert.fail();
                    return "";
                },
                lc -> {
                    Assert.assertEquals(1, lc.getLine());
                    Assert.assertEquals(2, lc.getColumn());
                    return "OK";
                });
    }

    @Test
    public void testRangePos() {
        final Position position = Position.ofLineWithRangeColumn(1, 3, 5);
        position.toRangePosition().map(range -> {
            Assert.assertEquals(1, range.getLine());
            Assert.assertEquals(3, range.getColumn());
            Assert.assertEquals(5, range.getEndColumn());
            return "";
        });
    }

}
