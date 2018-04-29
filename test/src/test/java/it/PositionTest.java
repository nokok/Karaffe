package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.base.pos.Position;

public class PositionTest {
    private final Position noPos = Position.noPos();

    private final Position pos1_0 = Position.of("A", 1, 0);
    private final Position pos1_1 = Position.of("A", 1, 1);
    private final Position pos1_2 = Position.of("A", 1, 2);
    private final Position pos1_3 = Position.of("A", 1, 3);
    private final Position pos1_5 = Position.of("A", 1, 5);

    private final Position pos2_0 = Position.of("A", 2, 0);
    private final Position pos2_5 = Position.of("A", 2, 5);

    private final Position pos3_0 = Position.of("A", 3, 0);

    private final Position range1_0to1_1 = this.pos1_0.merge(this.pos1_1);
    private final Position range1_0to1_2 = this.pos1_0.merge(this.pos1_2);
    private final Position range1_0to1_5 = this.pos1_0.merge(this.pos1_5);
    private final Position range1_0to2_5 = this.pos1_0.merge(this.pos2_5);
    private final Position range1_2to1_5 = this.pos1_2.merge(this.pos1_5);
    private final Position range2_5to3_0 = this.pos2_5.merge(this.pos3_0);

    private void runTest(final String toString, final Position position) {
        assertEquals(toString, position.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColNumberIsNegative() {
        Position.of("A", 1, -1);
    }

    @Test
    public void testCompareTo() {
        // 同じものどうしは0
        assertTrue(this.noPos.compareTo(this.noPos) == 0);
        assertTrue(this.pos1_0.compareTo(this.pos1_0) == 0);
        assertTrue(this.range1_0to2_5.compareTo(this.range1_0to2_5) == 0);

        // NoPosとの比較は常に NoPos < Position
        assertTrue(this.noPos.compareTo(this.pos1_0) < 0);
        assertTrue(this.noPos.compareTo(this.range1_0to2_5) < 0);
        assertTrue(this.pos1_0.compareTo(this.noPos) > 0);
        assertTrue(this.range1_0to2_5.compareTo(this.noPos) > 0);

        // LineColPos同士の比較は、行とカラム位置を見てソースコード上後から出てくるものが大きい
        assertTrue(this.pos1_0.compareTo(this.pos2_0) < 0);
        assertTrue(this.pos1_0.compareTo(this.pos1_1) < 0);
        assertTrue(this.pos2_0.compareTo(this.pos1_1) > 0);
        assertTrue(this.pos1_2.compareTo(this.pos1_1) > 0);
        assertTrue(this.pos1_1.compareTo(this.pos2_5) < 0);

        // LineColPosとRangeの比較は、Range.beginよりも前(=-1)、Rangeの範囲内(=0)、Range.endよりも後か(=1)で見る
        assertTrue(this.pos1_0.compareTo(this.range1_2to1_5) < 0);
        assertTrue(this.range1_2to1_5.compareTo(this.pos1_0) > 0);

        assertTrue(this.pos1_2.compareTo(this.range1_2to1_5) == 0);
        assertTrue(this.pos1_3.compareTo(this.range1_2to1_5) == 0);
        assertTrue(this.pos1_5.compareTo(this.range1_2to1_5) == 0);

        assertTrue(this.range1_2to1_5.compareTo(this.pos1_2) == 0);
        assertTrue(this.range1_2to1_5.compareTo(this.pos1_3) == 0);
        assertTrue(this.range1_2to1_5.compareTo(this.pos1_5) == 0);

        // Range同士の比較でBeginが同じ場合は、より広い方が大きいとみなす
        assertTrue(this.range1_0to1_1.compareTo(this.range1_0to1_5) < 0);
        assertTrue(this.range1_0to1_5.compareTo(this.range1_0to1_1) > 0);

        assertTrue(this.range1_0to1_1.compareTo(this.range1_0to1_2) < 0);
        assertTrue(this.range1_0to1_2.compareTo(this.range1_0to1_1) > 0);

        // 上記以外の場合は、Beginが先に出てくるほうが大きいとみなす
        assertTrue(this.range1_2to1_5.compareTo(this.range1_0to2_5) > 0);
        assertTrue(this.range1_0to2_5.compareTo(this.range1_2to1_5) < 0);

        assertTrue(this.range2_5to3_0.compareTo(this.range1_0to2_5) > 0);
        assertTrue(this.range1_0to2_5.compareTo(this.range2_5to3_0) < 0);

        assertTrue(this.range1_2to1_5.compareTo(this.range1_0to2_5) > 0);
        assertTrue(this.range1_0to2_5.compareTo(this.range1_2to1_5) < 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSourceName() {
        final Position pos1 = Position.of("A", 1, 1);
        final Position pos2 = Position.of("B", 0, 0);
        pos1.merge(pos2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSourceNameLineColMerge() {
        this.pos1_0.merge(Position.of("B", 1, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSourceNameRangeMerge() {
        this.range1_0to1_1.merge(Position.of("B", 1, 5));
    }

    @Test
    public void testMaxNoPos() {
        assertEquals(this.noPos, Position.max(this.noPos, this.noPos));
        assertEquals(this.pos1_0, Position.max(this.noPos, this.pos1_0));
        assertEquals(this.pos1_0, Position.max(this.pos1_0, this.noPos));
    }

    @Test
    public void testMergeLineColPos() {
        this.runTest("1:5~2:0 at A", this.pos1_5.merge(this.pos2_0));
        this.runTest("1:5~2:0 at A", this.pos2_0.merge(this.pos1_5));
        this.runTest("1:0~1:2 at A", this.pos1_0.merge(this.pos1_2));
        this.runTest("1:0~1:2 at A", this.pos1_2.merge(this.pos1_0));
    }

    @Test
    public void testMergeNoPos() {
        this.runTest("<no-pos>", this.noPos);

        final Position pos1 = Position.noPos();
        final Position pos2 = Position.of("source", 2, 30);
        final Position result = pos1.merge(pos2);
        assertEquals("2:30 at source", result.toString());
    }

    @Test
    public void testMergeRangePosition() {
        final Position pos1 = Position.of("source", 1, 5);
        final Position pos2 = Position.of("source", 2, 30);
        final Position merged1 = pos1.merge(pos2);
        final Position merged2 = pos2.merge(pos1);
        merged1.merge(merged2);
    }

    @Test
    public void testMinNoPos() {
        assertEquals(this.noPos, Position.min(this.noPos, this.noPos));
        assertEquals(this.pos1_0, Position.min(this.noPos, this.pos1_0));
        assertEquals(this.pos1_0, Position.min(this.pos1_0, this.noPos));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeLineNumber() {
        Position.of("A", -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroLineNumber() {
        Position.of("A", 0, 1);
    }
}
