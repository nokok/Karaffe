package it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.karaffe.compiler.pos.Position;

public class PositionTest {
    @Test
    public void testMerge() {
        Position pos1 = Position.of("source", 1, 5);
        Position pos2 = Position.of("source", 2, 30);
        Position result = pos1.merge(pos2);
        assertEquals("1:5~2:30 at source", result.toString());
    }

    @Test
    public void testMergeNoPos() {
        Position pos1 = Position.noPos();
        Position pos2 = Position.of("source", 2, 30);
        Position result = pos1.merge(pos2);
        assertEquals("2:30 at source", result.toString());
    }

    @Test
    public void testMergeRangePosition() {
        Position pos1 = Position.of("source", 1, 5);
        Position pos2 = Position.of("source", 2, 30);
        Position merged1 = pos1.merge(pos2);
        Position merged2 = pos2.merge(pos1);
        merged1.merge(merged2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidSourceName() {
        Position pos1 = Position.of("A", 1, 1);
        Position pos2 = Position.of("B", 0, 0);
        pos1.merge(pos2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroLineNumber() {
        Position.of("A", 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeLineNumber() {
        Position.of("A", -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColNumberIsNegative() {
        Position.of("A", 1, -1);
    }

    @Test
    public void testCompareTo() {
        Position noPos = Position.noPos();

        Position pos1_0 = Position.of("", 1, 0);
        Position pos1_1 = Position.of("", 1, 1);
        Position pos1_2 = Position.of("", 1, 2);
        Position pos1_3 = Position.of("", 1, 3);
        Position pos1_5 = Position.of("", 1, 5);

        Position pos2_0 = Position.of("", 2, 0);
        Position pos2_5 = Position.of("", 2, 5);

        Position pos3_0 = Position.of("", 3, 0);

        Position range1_0to1_1 = pos1_0.merge(pos1_1);
        Position range1_0to1_2 = pos1_0.merge(pos1_2);
        Position range1_0to1_5 = pos1_0.merge(pos1_5);
        Position range1_0to2_5 = pos1_0.merge(pos2_5);
        Position range1_2to1_5 = pos1_2.merge(pos1_5);
        Position range2_5to3_0 = pos2_5.merge(pos3_0);

        // 同じものどうしは0
        assertTrue(noPos.compareTo(noPos) == 0);
        assertTrue(pos1_0.compareTo(pos1_0) == 0);
        assertTrue(range1_0to2_5.compareTo(range1_0to2_5) == 0);

        // NoPosとの比較は常に NoPos < Position
        assertTrue(noPos.compareTo(pos1_0) < 0);
        assertTrue(noPos.compareTo(range1_0to2_5) < 0);
        assertTrue(pos1_0.compareTo(noPos) > 0);
        assertTrue(range1_0to2_5.compareTo(noPos) > 0);

        // LineColPos同士の比較は、行とカラム位置を見てソースコード上後から出てくるものが大きい
        assertTrue(pos1_0.compareTo(pos2_0) < 0);
        assertTrue(pos1_0.compareTo(pos1_1) < 0);
        assertTrue(pos2_0.compareTo(pos1_1) > 0);
        assertTrue(pos1_2.compareTo(pos1_1) > 0);
        assertTrue(pos1_1.compareTo(pos2_5) < 0);

        // LineColPosとRangeの比較は、Range.beginよりも前(=-1)、Rangeの範囲内(=0)、Range.endよりも後か(=1)で見る
        assertTrue(pos1_0.compareTo(range1_2to1_5) < 0);
        assertTrue(range1_2to1_5.compareTo(pos1_0) > 0);

        assertTrue(pos1_2.compareTo(range1_2to1_5) == 0);
        assertTrue(pos1_3.compareTo(range1_2to1_5) == 0);
        assertTrue(pos1_5.compareTo(range1_2to1_5) == 0);

        assertTrue(range1_2to1_5.compareTo(pos1_2) == 0);
        assertTrue(range1_2to1_5.compareTo(pos1_3) == 0);
        assertTrue(range1_2to1_5.compareTo(pos1_5) == 0);

        // Range同士の比較でBeginが同じ場合は、より広い方が大きいとみなす
        assertTrue(range1_0to1_1.compareTo(range1_0to1_5) < 0);
        assertTrue(range1_0to1_5.compareTo(range1_0to1_1) > 0);

        assertTrue(range1_0to1_1.compareTo(range1_0to1_2) < 0);
        assertTrue(range1_0to1_2.compareTo(range1_0to1_1) > 0);

        // 上記以外の場合は、Beginが先に出てくるほうが大きいとみなす
        assertTrue(range1_2to1_5.compareTo(range1_0to2_5) > 0);
        assertTrue(range1_0to2_5.compareTo(range1_2to1_5) < 0);

        assertTrue(range2_5to3_0.compareTo(range1_0to2_5) > 0);
        assertTrue(range1_0to2_5.compareTo(range2_5to3_0) < 0);

        assertTrue(range1_2to1_5.compareTo(range1_0to2_5) > 0);
        assertTrue(range1_0to2_5.compareTo(range1_2to1_5) < 0);

    }

}
